package com.company.repositories;

import com.company.models.User;

// UserRepository extends BaseRepository for common CRUD operations
public interface UserRepository extends BaseRepository<User, Integer> {
    // User-specific methods can be added here if needed
}
