package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsernameAndPassword(String username,String password);
    Boolean existsByUsername(String username);

    List<User> findByUsernameNot(String username);

    List<User> findByUsernameContainingAndUsernameNot(String keyword, String currentUser);
}
