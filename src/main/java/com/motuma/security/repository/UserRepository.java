package com.motuma.security.repository;

import com.motuma.security.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Boolean existsByUserName (String username);
    Optional<User> findByUserName(String username);
}
