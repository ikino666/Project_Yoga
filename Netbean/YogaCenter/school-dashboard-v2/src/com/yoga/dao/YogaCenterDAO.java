/*
Trần Trung Tín - PC07488
SD18309
 */
package com.yoga.dao;

import java.util.List;

abstract public interface YogaCenterDAO<EntityType, KeyType> {
    
    abstract public void insert(EntityType entity);

    abstract public void update(EntityType entity);

    abstract public void delete(KeyType id);

    abstract public EntityType selectById(KeyType id);

    abstract public List<EntityType> selectAll();

    abstract List<EntityType> selectBySQL(String sql, Object... args);
}


