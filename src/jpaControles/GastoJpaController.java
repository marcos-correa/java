/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jpaControles;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import bancoDados.Cartao;
import bancoDados.Gasto;
import bancoDados.TipoGasto;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpaControles.exceptions.NonexistentEntityException;
import jpaControles.exceptions.PreexistingEntityException;

/**
 *
 * @author dinha
 */
public class GastoJpaController implements Serializable {

    public GastoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Gasto gasto) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cartao idCartao = gasto.getIdCartao();
            if (idCartao != null) {
                idCartao = em.getReference(idCartao.getClass(), idCartao.getIdCartao());
                gasto.setIdCartao(idCartao);
            }
            TipoGasto idTipoGasto = gasto.getIdTipoGasto();
            if (idTipoGasto != null) {
                idTipoGasto = em.getReference(idTipoGasto.getClass(), idTipoGasto.getIdTipoGasto());
                gasto.setIdTipoGasto(idTipoGasto);
            }
            em.persist(gasto);
            if (idCartao != null) {
                idCartao.getGastoCollection().add(gasto);
                idCartao = em.merge(idCartao);
            }
            if (idTipoGasto != null) {
                idTipoGasto.getGastoCollection().add(gasto);
                idTipoGasto = em.merge(idTipoGasto);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findGasto(gasto.getIdGasto()) != null) {
                throw new PreexistingEntityException("Gasto " + gasto + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Gasto gasto) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Gasto persistentGasto = em.find(Gasto.class, gasto.getIdGasto());
            Cartao idCartaoOld = persistentGasto.getIdCartao();
            Cartao idCartaoNew = gasto.getIdCartao();
            TipoGasto idTipoGastoOld = persistentGasto.getIdTipoGasto();
            TipoGasto idTipoGastoNew = gasto.getIdTipoGasto();
            if (idCartaoNew != null) {
                idCartaoNew = em.getReference(idCartaoNew.getClass(), idCartaoNew.getIdCartao());
                gasto.setIdCartao(idCartaoNew);
            }
            if (idTipoGastoNew != null) {
                idTipoGastoNew = em.getReference(idTipoGastoNew.getClass(), idTipoGastoNew.getIdTipoGasto());
                gasto.setIdTipoGasto(idTipoGastoNew);
            }
            gasto = em.merge(gasto);
            if (idCartaoOld != null && !idCartaoOld.equals(idCartaoNew)) {
                idCartaoOld.getGastoCollection().remove(gasto);
                idCartaoOld = em.merge(idCartaoOld);
            }
            if (idCartaoNew != null && !idCartaoNew.equals(idCartaoOld)) {
                idCartaoNew.getGastoCollection().add(gasto);
                idCartaoNew = em.merge(idCartaoNew);
            }
            if (idTipoGastoOld != null && !idTipoGastoOld.equals(idTipoGastoNew)) {
                idTipoGastoOld.getGastoCollection().remove(gasto);
                idTipoGastoOld = em.merge(idTipoGastoOld);
            }
            if (idTipoGastoNew != null && !idTipoGastoNew.equals(idTipoGastoOld)) {
                idTipoGastoNew.getGastoCollection().add(gasto);
                idTipoGastoNew = em.merge(idTipoGastoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = gasto.getIdGasto();
                if (findGasto(id) == null) {
                    throw new NonexistentEntityException("The gasto with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Gasto gasto;
            try {
                gasto = em.getReference(Gasto.class, id);
                gasto.getIdGasto();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The gasto with id " + id + " no longer exists.", enfe);
            }
            Cartao idCartao = gasto.getIdCartao();
            if (idCartao != null) {
                idCartao.getGastoCollection().remove(gasto);
                idCartao = em.merge(idCartao);
            }
            TipoGasto idTipoGasto = gasto.getIdTipoGasto();
            if (idTipoGasto != null) {
                idTipoGasto.getGastoCollection().remove(gasto);
                idTipoGasto = em.merge(idTipoGasto);
            }
            em.remove(gasto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Gasto> findGastoEntities() {
        return findGastoEntities(true, -1, -1);
    }

    public List<Gasto> findGastoEntities(int maxResults, int firstResult) {
        return findGastoEntities(false, maxResults, firstResult);
    }

    private List<Gasto> findGastoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Gasto.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Gasto findGasto(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Gasto.class, id);
        } finally {
            em.close();
        }
    }

    public int getGastoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Gasto> rt = cq.from(Gasto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
