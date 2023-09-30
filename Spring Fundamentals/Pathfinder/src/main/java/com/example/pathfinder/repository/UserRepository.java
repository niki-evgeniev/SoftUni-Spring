package com.example.pathfinder.repository;

import com.example.pathfinder.models.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
