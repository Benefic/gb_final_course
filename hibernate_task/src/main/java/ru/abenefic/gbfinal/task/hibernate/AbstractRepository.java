package ru.abenefic.gbfinal.task.hibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class AbstractRepository<T> {

    private final EntityManager entityManager;
    private final Class<T> typeOfT;

    @SuppressWarnings("unchecked")
    AbstractRepository() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ru.abenefic.gbfinal.task.hibernate");
        entityManager = entityManagerFactory.createEntityManager();
        this.typeOfT = (Class<T>)
                ((ParameterizedType) getClass()
                        .getGenericSuperclass())
                        .getActualTypeArguments()[0];
    }

    void save(T entity) {
        entityManager.persist(entity);
    }

    T findById(Long id) {
        return entityManager.find(typeOfT, id);
    }

    List<T> findAll() {
        entityManager.getTransaction().begin();
        TypedQuery<T> query = entityManager.createQuery("FROM " + typeOfT.getSimpleName(), typeOfT);
        List<T> list = query.getResultList();
        entityManager.getTransaction().commit();
        return list;
    }

    void remove(T entity){
        entityManager.remove(entity);
    }
}
