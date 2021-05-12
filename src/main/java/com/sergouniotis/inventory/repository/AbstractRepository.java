package com.sergouniotis.inventory.repository;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class AbstractRepository<T, K> implements Repository<T, K> {

    @Inject
    Logger log;

    private Class<T> type;

    @PersistenceContext(unitName = "JPADatasourceBPRPU")
    protected EntityManager em;

    protected AbstractRepository(Class<T> type) {
        this.type = type;
    }

    @Override
    public List<T> findAll() {
        TypedQuery<T> query = em.createQuery("SELECT T FROM " + type.getSimpleName() + " T", type);
        return query.setMaxResults(MAX_RESULTS).getResultList();
    }

    @Override
    public List<T> findAll(int offset, int limit) {
        log.info("find all");
        TypedQuery<T> query = em.createQuery("SELECT T FROM " + type.getSimpleName() + " T", type);
        query.setFirstResult(offset);
        return query.setMaxResults(limit).getResultList();
    }

    @Override
    public T save(T entity) {
        em.persist(entity);
        return entity;
    }

    @Override
    public T getReference(K key) {
        try {
            return em.getReference(type, key);
        } catch (EntityNotFoundException | IllegalArgumentException e) {
            log.log(Level.INFO, String.format("Cannot find entity with id %s", String.valueOf(key)), e);
            return null;
        }
    }

    @Override
    public T find(K key) {
        return em.find(type, key);
    }

    @Override
    public void delete(T entity) {
        this.em.remove(entity);
    }

    @Override
    public T merge(T entity) {
        return this.em.merge(entity);
    }

    @Override
    public Collection<T> save(Collection<T> entities) {
        if (Objects.nonNull(entities) && entities.size() > 0) {
            for (T entity : entities) {
                save(entity);
            }
        }
        return entities;
    }

    @Override
    public Collection<T> merge(Collection<T> entities) {
        if (Objects.nonNull(entities) && entities.size() > 0) {
            for (T entity : entities) {
                merge(entity);
            }
        }
        return entities;
    }

    @Override
    public void delete(Collection<T> entities) {
        entities.stream().forEach(em::remove);
        Optional.ofNullable(entities).orElse(Collections.emptyList()).clear();
        em.flush();
    }

    @Override
    public T update(T entity) {
        // the specified entity is persistent so we are flushing the changes on
        // the persistent object and not wait for commit and then refresh to get
        // the new status of the persistent object
        em.flush();
        em.refresh(entity);
        return entity;
    }

    @Override
    public Class<T> getEntityType() {
        return this.type;
    }

}

