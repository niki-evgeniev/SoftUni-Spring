package com.example.pathfinder.repository;

import com.example.pathfinder.models.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
