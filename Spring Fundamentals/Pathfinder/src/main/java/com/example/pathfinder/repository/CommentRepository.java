package com.example.pathfinder.repository;

import com.example.pathfinder.models.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
