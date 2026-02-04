package com.company.repositories;
import java.util.List;

// Generic base repository interface with common CRUD operations
// T = Entity type, ID = Primary key type
public interface BaseRepository<T, ID> {
    
    // Create/Save a new entity
    void create(T entity);
    
    // Find entity by id
    T findById(ID id);
    
    // Find all entities
    List<T> findAll();
    
    // Update an existing entity
    void update(T entity);
    
    // Delete entity by id
    void delete(ID id);
    
    // Check if entity exists
    boolean exists(ID id);
}
