package com.company.repositories;

import com.company.models.User;
import java.util.List;

public interface UserRepository {

    // Create new user
    void create(User user);

    // Get all users
    List<User> findAll();

    // Find user by id
    User findById(int id);
}
