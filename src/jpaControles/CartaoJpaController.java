/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jpaControles;

import bancoDados.Cartao;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import bancoDados.Gasto;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpaControles.exceptions.NonexistentEntityException;
import jpaControles.exceptions.PreexistingEntityException;

/**
 *
 * @author dinha
 */
public class CartaoJpaController implements Serializable {

    public CartaoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cartao cartao) throws PreexistingEntityException, Exception {
        if (cartao.getGastoCollection() == null) {
            cartao.setGastoCollection(new ArrayList<Gasto>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Gasto> attachedGastoCollection = new ArrayList<Gasto>();
            for (Gasto gastoCollectionGastoToAttach : cartao.getGastoCollection()) {
                gastoCollectionGastoToAttach = em.getReference(gastoCollectionGastoToAttach.getClass(), gastoCollectionGastoToAttach.getIdGasto());
                attachedGastoCollection.add(gastoCollectionGastoToAttach);
            }
            cartao.setGastoCollection(attachedGastoCollection);
            em.persist(cartao);
            for (Gasto gastoCollectionGasto : cartao.getGastoCollection()) {
                Cartao oldIdCartaoOfGastoCollectionGasto = gastoCollectionGasto.getIdCartao();
                gastoCollectionGasto.setIdCartao(cartao);
                gastoCollectionGasto = em.merge(gastoCollectionGasto);
                if (oldIdCartaoOfGastoCollectionGasto != null) {
                    oldIdCartaoOfGastoCollectionGasto.getGastoCollection().remove(gastoCollectionGasto);
                    oldIdCartaoOfGastoCollectionGasto = em.merge(oldIdCartaoOfGastoCollectionGasto);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCartao(cartao.getIdCartao()) != null) {
                throw new PreexistingEntityException("Cartao " + cartao + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cartao cartao) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cartao persistentCartao = em.find(Cartao.class, cartao.getIdCartao());
            Collection<Gasto> gastoCollectionOld = persistentCartao.getGastoCollection();
            Collection<Gasto> gastoCollectionNew = cartao.getGastoCollection();
            Collection<Gasto> attachedGastoCollectionNew = new ArrayList<Gasto>();
            for (Gasto gastoCollectionNewGastoToAttach : gastoCollectionNew) {
                gastoCollectionNewGastoToAttach = em.getReference(gastoCollectionNewGastoToAttach.getClass(), gastoCollectionNewGastoToAttach.getIdGasto());
                attachedGastoCollectionNew.add(gastoCollectionNewGastoToAttach);
            }
            gastoCollectionNew = attachedGastoCollectionNew;
            cartao.setGastoCollection(gastoCollectionNew);
            cartao = em.merge(cartao);
            for (Gasto gastoCollectionOldGasto : gastoCollectionOld) {
                if (!gastoCollectionNew.contains(gastoCollectionOldGasto)) {
                    gastoCollectionOldGasto.setIdCartao(null);
                    gastoCollectionOldGasto = em.merge(gastoCollectionOldGasto);
                }
            }
            for (Gasto gastoCollectionNewGasto : gastoCollectionNew) {
                if (!gastoCollectionOld.contains(gastoCollectionNewGasto)) {
                    Cartao oldIdCartaoOfGastoCollectionNewGasto = gastoCollectionNewGasto.getIdCartao();
                    gastoCollectionNewGasto.setIdCartao(cartao);
                    gastoCollectionNewGasto = em.merge(gastoCollectionNewGasto);
                    if (oldIdCartaoOfGastoCollectionNewGasto != null && !oldIdCartaoOfGastoCollectionNewGasto.equals(cartao)) {
                        oldIdCartaoOfGastoCollectionNewGasto.getGastoCollection().remove(gastoCollectionNewGasto);
                        oldIdCartaoOfGastoCollectionNewGasto = em.merge(oldIdCartaoOfGastoCollectionNewGasto);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = cartao.getIdCartao();
                if (findCartao(id) == null) {
                    throw new NonexistentEntityException("The cartao with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cartao cartao;
            try {
                cartao = em.getReference(Cartao.class, id);
                cartao.getIdCartao();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cartao with id " + id + " no longer exists.", enfe);
            }
            Collection<Gasto> gastoCollection = cartao.getGastoCollection();
            for (Gasto gastoCollectionGasto : gastoCollection) {
                gastoCollectionGasto.setIdCartao(null);
                gastoCollectionGasto = em.merge(gastoCollectionGasto);
            }
            em.remove(cartao);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cartao> findCartaoEntities() {
        return findCartaoEntities(true, -1, -1);
    }

    public List<Cartao> findCartaoEntities(int maxResults, int firstResult) {
        return findCartaoEntities(false, maxResults, firstResult);
    }

    private List<Cartao> findCartaoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cartao.class));
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

    public Cartao findCartao(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cartao.class, id);
        } finally {
            em.close();
        }
    }

    public int getCartaoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cartao> rt = cq.from(Cartao.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
