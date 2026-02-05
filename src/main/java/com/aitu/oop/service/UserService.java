package com.aitu.oop.service;

import com.aitu.oop.entity.User;
import com.aitu.oop.repository.UserRepository;

public class UserService {

    private final UserRepository userRepository;
    
    
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User login(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        if (!(user.getPassword().equals(password))) {
            throw new RuntimeException("Invalid password");
        }

        return user;

    }

    public boolean isAdmin(User user) {
        return "ADMIN".equalsIgnoreCase(user.getRole());
    }

    public boolean isUser(User user) {
        return "USER".equalsIgnoreCase(user.getRole());
    }

    public void checkAdminAccess(User user) {
        if (!(isAdmin(user))) {
            throw new RuntimeException("No access. Admin only");
        }
    }

    public User register(User user) {
        if (user.getUserName() == null || user.getUserName().isEmpty()) {
            throw new IllegalArgumentException("Username is required");
        }
        if (user.getPassword() == null || user.getPassword().length() < 5) {
            throw new IllegalArgumentException("Password is too short");
        }
        if (userRepository.findByUsername(user.getUserName()) != null) {
            throw new IllegalArgumentException("Username already exists");
        } 

        user.setRole("USER");
        userRepository.addUser(user);
        return user;
    }


    public User findById(int Id){
        return userRepository.findById(Id);
    }
}
