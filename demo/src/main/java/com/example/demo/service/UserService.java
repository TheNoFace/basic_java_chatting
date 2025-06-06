package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void saveUser(User user) {
        user.setUsername(user.getUsername());
        user.setPassword(user.getPassword()); 
        userRepository.save(user);
    }

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public List<User> getAllUsersExcept(String username) {
        return userRepository.findByUsernameNot(username);
    }

    public User findByUsernameAndByPassword(String username ,String password) {
        return userRepository.findByUsernameAndPassword(username,password).orElse(null);
    }

    public boolean matchesPassword(String rawPassword, String storedPassword) {
        return rawPassword.equals(storedPassword); 
    }

    public List<User> searchByNameExcludingSelf(String keyword, String currentUser) {
        return userRepository.findByUsernameContainingAndUsernameNot(keyword, currentUser);
    }
    
    
}

