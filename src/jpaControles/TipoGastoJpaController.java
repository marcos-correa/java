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
import bancoDados.Gasto;
import bancoDados.TipoGasto;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpaControles.exceptions.IllegalOrphanException;
import jpaControles.exceptions.NonexistentEntityException;
import jpaControles.exceptions.PreexistingEntityException;

/**
 *
 * @author dinha
 */
public class TipoGastoJpaController implements Serializable {

    public TipoGastoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TipoGasto tipoGasto) throws PreexistingEntityException, Exception {
        if (tipoGasto.getGastoCollection() == null) {
            tipoGasto.setGastoCollection(new ArrayList<Gasto>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Gasto> attachedGastoCollection = new ArrayList<Gasto>();
            for (Gasto gastoCollectionGastoToAttach : tipoGasto.getGastoCollection()) {
                gastoCollectionGastoToAttach = em.getReference(gastoCollectionGastoToAttach.getClass(), gastoCollectionGastoToAttach.getIdGasto());
                attachedGastoCollection.add(gastoCollectionGastoToAttach);
            }
            tipoGasto.setGastoCollection(attachedGastoCollection);
            em.persist(tipoGasto);
            for (Gasto gastoCollectionGasto : tipoGasto.getGastoCollection()) {
                TipoGasto oldIdTipoGastoOfGastoCollectionGasto = gastoCollectionGasto.getIdTipoGasto();
                gastoCollectionGasto.setIdTipoGasto(tipoGasto);
                gastoCollectionGasto = em.merge(gastoCollectionGasto);
                if (oldIdTipoGastoOfGastoCollectionGasto != null) {
                    oldIdTipoGastoOfGastoCollectionGasto.getGastoCollection().remove(gastoCollectionGasto);
                    oldIdTipoGastoOfGastoCollectionGasto = em.merge(oldIdTipoGastoOfGastoCollectionGasto);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTipoGasto(tipoGasto.getIdTipoGasto()) != null) {
                throw new PreexistingEntityException("TipoGasto " + tipoGasto + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TipoGasto tipoGasto) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoGasto persistentTipoGasto = em.find(TipoGasto.class, tipoGasto.getIdTipoGasto());
            Collection<Gasto> gastoCollectionOld = persistentTipoGasto.getGastoCollection();
            Collection<Gasto> gastoCollectionNew = tipoGasto.getGastoCollection();
            List<String> illegalOrphanMessages = null;
            for (Gasto gastoCollectionOldGasto : gastoCollectionOld) {
                if (!gastoCollectionNew.contains(gastoCollectionOldGasto)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Gasto " + gastoCollectionOldGasto + " since its idTipoGasto field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Gasto> attachedGastoCollectionNew = new ArrayList<Gasto>();
            for (Gasto gastoCollectionNewGastoToAttach : gastoCollectionNew) {
                gastoCollectionNewGastoToAttach = em.getReference(gastoCollectionNewGastoToAttach.getClass(), gastoCollectionNewGastoToAttach.getIdGasto());
                attachedGastoCollectionNew.add(gastoCollectionNewGastoToAttach);
            }
            gastoCollectionNew = attachedGastoCollectionNew;
            tipoGasto.setGastoCollection(gastoCollectionNew);
            tipoGasto = em.merge(tipoGasto);
            for (Gasto gastoCollectionNewGasto : gastoCollectionNew) {
                if (!gastoCollectionOld.contains(gastoCollectionNewGasto)) {
                    TipoGasto oldIdTipoGastoOfGastoCollectionNewGasto = gastoCollectionNewGasto.getIdTipoGasto();
                    gastoCollectionNewGasto.setIdTipoGasto(tipoGasto);
                    gastoCollectionNewGasto = em.merge(gastoCollectionNewGasto);
                    if (oldIdTipoGastoOfGastoCollectionNewGasto != null && !oldIdTipoGastoOfGastoCollectionNewGasto.equals(tipoGasto)) {
                        oldIdTipoGastoOfGastoCollectionNewGasto.getGastoCollection().remove(gastoCollectionNewGasto);
                        oldIdTipoGastoOfGastoCollectionNewGasto = em.merge(oldIdTipoGastoOfGastoCollectionNewGasto);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tipoGasto.getIdTipoGasto();
                if (findTipoGasto(id) == null) {
                    throw new NonexistentEntityException("The tipoGasto with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoGasto tipoGasto;
            try {
                tipoGasto = em.getReference(TipoGasto.class, id);
                tipoGasto.getIdTipoGasto();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoGasto with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Gasto> gastoCollectionOrphanCheck = tipoGasto.getGastoCollection();
            for (Gasto gastoCollectionOrphanCheckGasto : gastoCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This TipoGasto (" + tipoGasto + ") cannot be destroyed since the Gasto " + gastoCollectionOrphanCheckGasto + " in its gastoCollection field has a non-nullable idTipoGasto field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(tipoGasto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TipoGasto> findTipoGastoEntities() {
        return findTipoGastoEntities(true, -1, -1);
    }

    public List<TipoGasto> findTipoGastoEntities(int maxResults, int firstResult) {
        return findTipoGastoEntities(false, maxResults, firstResult);
    }

    private List<TipoGasto> findTipoGastoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TipoGasto.class));
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

    public TipoGasto findTipoGasto(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipoGasto.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoGastoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TipoGasto> rt = cq.from(TipoGasto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
