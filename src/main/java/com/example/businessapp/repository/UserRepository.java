package com.example.businessapp.repository;

import com.example.businessapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    List<User> findUserByActiveIsTrue();

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}
