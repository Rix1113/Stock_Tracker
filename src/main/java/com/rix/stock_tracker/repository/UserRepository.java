package com.rix.stock_tracker.repository;

import com.rix.stock_tracker.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u from User u where u.email = ?1")
    public User findByEmail(String email);
}
