package com.raja.scol.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.raja.scol.entities.User;

public interface UserRepository extends JpaRepository<User, String> {
    User findByEmailAndPassword(String email, String password);
}
