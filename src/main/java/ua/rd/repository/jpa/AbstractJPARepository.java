package ua.rd.repository.jpa;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public abstract class AbstractJPARepository<E> {

    @PersistenceContext
    protected EntityManager em;

    private Class<?> entityClass;

    public AbstractJPARepository() {
        super();
        this.entityClass = extractEntityClass();
    }

    public AbstractJPARepository(EntityManager em) {
        super();
        this.em = em;
        this.entityClass = extractEntityClass();
    }

    public E getById(Long id) {
        return (E) em.find(entityClass, id);
    }

    @Transactional
    public E save(E entity) {
//        em.persist(entity);
//        return entity;
        return em.merge(entity);
    }

    @Transactional
    public boolean remove(Long id) {
        E entityToRemove = (E) em.find(entityClass, id);
        if (entityToRemove == null) {
            return false;
        }
        em.remove(entityToRemove);
        return true;
    }

    public List<E> findAll(String queryName) {
        return (List<E>) em.createNamedQuery(queryName, entityClass).getResultList();
    }

    private Class<E> extractEntityClass() {
        return (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public Class<?> getEntityClass() {
        return entityClass;
    }

    public void setEntityClass(Class<?> entityClass) {
        this.entityClass = entityClass;
    }
}
