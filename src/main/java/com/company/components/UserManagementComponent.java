package com.company.components;

import java.util.List;
import com.company.models.User;
import com.company.repositories.UserRepository;

// Component that groups user-related repository operations
public class UserManagementComponent {
    private final UserRepository userRepository;

    public UserManagementComponent(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createUser(User user) {
        userRepository.create(user);
    }

    public User getUser(int id) {
        return userRepository.findById(id);
    }

    public List<User> listUsers() {
        return userRepository.findAll();
    }

    public void updateUser(User user) {
        userRepository.update(user);
    }

    public void deleteUser(int id) {
        userRepository.delete(id);
    }
}
