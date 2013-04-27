package com.pushpop.server.dao;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.pushpop.server.domain.IDatastoreObject;
import com.pushpop.shared.PushPopException;


public class DaoBase<T extends IDatastoreObject> {

    private final Logger logger = Logger.getLogger(DaoBase.class.getName());

    protected Class<T> clazz;

    @SuppressWarnings("unchecked")
    public DaoBase() {
        clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public T create() {
        try {
            T obj = clazz.newInstance();
            return persist(obj);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * This uses the merge function and not the persist function. That way it
     * can be used to update and create a new entity. There are some small
     * caveats with this but nothing major.
     * http://stackoverflow.com/questions/1069992
     * /jpa-entitymanager-why-use-persist-over-merge
     * 
     * @param Object
     *            to be persisted.
     * @return The persisted object
     */
    public T persist(T obj) {
        EntityManager em = ThreadLocalPersistenceManager.getEntityManager();

        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            obj = em.merge(obj);
        } finally {
            if (!tx.getRollbackOnly()) {
                tx.commit();
            }
        }

        return obj;
    }

    /**
     * This will save the object and return the id of the persisted object.
     * 
     * @param obj
     * @return
     */
    public Long save(T obj) {
        persist(obj);
        return obj.getId();
    }

    public T saveAndReturn(T obj) {
        return persist(obj);
    }

    /**
     * 
     * @param id
     * @return
     * @throws EntityNotFoundException
     */
    public T find(Long id) {
        if (id == null) {
            return null;
        }
        EntityManager em = ThreadLocalPersistenceManager.getEntityManager();

        T obj = em.find(clazz, id);
        return obj;
    }

    public List<T> findList(Set<Long> ids) {
        EntityManager em = ThreadLocalPersistenceManager.getEntityManager();

        TypedQuery<T> q = em.createQuery("select entity from " + clazz.getName() + " entity where entity.id IN :ids", clazz);
        q.setParameter("ids", ids);

        return (List<T>) q.getResultList();
    }

 
    @SuppressWarnings("unchecked")
    public List<T> listAll() {
        EntityManager em = ThreadLocalPersistenceManager.getEntityManager();

        Query query = em.createQuery("SELECT e FROM " + clazz.getName() + " e");
        return query.getResultList();
    }

    /**
     * The type checking is done with the client Generic.
     * 
     * @param clazz
     * @return
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static <T> T listAll(Class clazz) {
        EntityManager em = ThreadLocalPersistenceManager.getEntityManager();

        Query query = em.createQuery("SELECT e FROM " + clazz.getName() + " e");
        return (T) query.getResultList();
    }

    /**
     * Finds a class with a string id.
     * 
     * @param <T>
     * @param clazz
     * @param id
     * @return
     */
    public static <T> T findByClass(Class<? extends T> clazz, Long id) {
        if (id == null) {
            return null;
        }
        EntityManager em = ThreadLocalPersistenceManager.getEntityManager();

        T obj = (T) em.find(clazz, id);
        return obj;

    }

    @SuppressWarnings("unchecked")
    public T copy(Long id) throws PushPopException {
        try {
            return (T) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new PushPopException("Unable to clone object", e);
        }
    }

    /**
     * Convenience method to get all objects matching a single property
     * 
     * @param propName
     * @param propValue
     * @return T matching Object
     * @throws TooManyResultsException
     */
    @SuppressWarnings("unchecked")
    // TODO FIX THIS
    public T getByProperty(String propName, String propValue)  {
        EntityManager em = ThreadLocalPersistenceManager.getEntityManager();

        Query query = em.createQuery(propName + " == :propValue");
        T obj = (T) query.getSingleResult();
        return obj;
    }

    public List<Long> fetchIds() {
        List<Long> ids = new ArrayList<Long>();

        List<T> objs = listAll();

        for (T obj : objs) {
            ids.add(obj.getId());
        }

        return ids;
    }

    /**
     * This will remove a object
     * 
     */
    public void delete(Long id) {
        EntityManager em = ThreadLocalPersistenceManager.getEntityManager();
        T attached = (T) em.find(clazz, id);
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.remove(attached);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Unable to delete the entity with ID " + id, e);
            tx.rollback();
        } finally {
            if (tx.isActive()) {
                tx.commit();
            }
        }
    }

    /**
     * Bulk delete much more efficient.
     * 
     * JPQL bulk delete operation so that all options are deleted in single
     * query.
     * 
     * @return Integer number of items deleted.
     * @param idsToDelete
     */
    public Integer deleteList(Set<Long> idList) {
        List<T> deleteRecords = findList(idList);
        EntityManager em = ThreadLocalPersistenceManager.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        int deletedSize = 0;
        for (T attached : deleteRecords) {
            try {
                tx.begin();
                em.remove(attached);
                deletedSize++;
            } catch (Exception e) {
                logger.log(Level.SEVERE, "Unable to delete the entity with ID " + attached.getId(), e);
                tx.rollback();
            } finally {
                if (tx.isActive()) {
                    tx.commit();
                }
            }
        }
        return deletedSize;
    }

    /**
     * This is slow and inefficient and should be converted to use the Cursor
     * ability. In the future this will be removed and converted to use Cursor
     * for data retrieval. I have marked it deprecated prematurely so that I do
     * not forget to fix this.
     * 
     * TODO change findRange to use cursor syntax.
     * 
     * @param start
     *            The start of how many entities to return.
     * @param max
     *            The maximum number of entities to return.
     * @return List of objects in the range.
     */
    @SuppressWarnings("unchecked")
    public List<T> findRange(int start, int max) {
        EntityManager em = getEntityManager();
        Query query = em.createQuery("SELECT e FROM " + clazz.getName() + " e");

        query.setFirstResult(start);
        query.setMaxResults(max);
        em.getTransaction().begin();
        List<T> resultList = (List<T>) query.getResultList();
        em.getTransaction().commit();
        
        return resultList;
    }
    

    /**
     * This is slow and inefficient and should be converted to use the Cursor
     * ability. In the future this will be removed and converted to use Cursor
     * for data retrieval. I have marked it deprecated prematurely so that I do
     * not forget to fix this.
     * 
     * TODO change findRange to use cursor syntax.
     * 
     * @param start
     *            The start of how many entities to return.
     * @param max
     *            The maximum number of entities to return.
     * @return List of objects in the range.
     */
    @SuppressWarnings("unchecked")
    public List<T> findRange(int start, int max, String sortColumn, String sortOrder) {
        EntityManager em = ThreadLocalPersistenceManager.getEntityManager();
        Query query = em.createQuery("SELECT e FROM " + clazz.getName() + " e");

        query.setFirstResult(start);
        query.setMaxResults(max);
        
        List<T> resultList = (List<T>) query.getResultList();
        resultList.size();
        return resultList;
    }

    

    public List<T> search(String search) {
        throw new RuntimeException("Implement me with builder api");
    }

    protected static EntityManager getEntityManager() {
        return ThreadLocalPersistenceManager.getEntityManager();
    }
}