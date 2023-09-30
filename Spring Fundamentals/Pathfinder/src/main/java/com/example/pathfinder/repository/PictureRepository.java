package com.example.pathfinder.repository;

import com.example.pathfinder.models.entity.Picture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PictureRepository extends JpaRepository<Picture, Long> {
}
