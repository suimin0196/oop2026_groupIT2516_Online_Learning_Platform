package com.company.services;
import java.util.List;

// Generic base service interface
// T = Entity type, ID = Primary key type
public interface BaseService<T, ID> {
    
    // Create a new entity
    void create(T entity);
    
    // Get entity by id
    T getById(ID id);
    
    
    // Update an entity
    void update(T entity);
    // Get all entities
    List<T> getAll();
    
    // Delete an entity by id
    void delete(ID id);
    
    // Check if entity exists
    boolean exists(ID id);
}
