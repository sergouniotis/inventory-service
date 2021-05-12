package com.sergouniotis.inventory.repository;


import java.util.Collection;
import java.util.List;

public interface Repository<T, K> {

    public static final int MAX_RESULTS = 5000;

    T save(T entity);

    T find(K key);

    Collection<T> findAll();

    /**
     * Find the first items limited by limit for the specified page.
     *
     * @param offset the current page
     * @param limit  size of the page
     * @return the collection
     */
    List<T> findAll(int offset, int limit);

    void delete(T entity);

    Collection<T> save(Collection<T> entities);

    T merge(T entity);

    Collection<T> merge(Collection<T> entities);

    void delete(Collection<T> entities);

    T update(T entity);

    T getReference(K key);

    Class<T> getEntityType();

}
