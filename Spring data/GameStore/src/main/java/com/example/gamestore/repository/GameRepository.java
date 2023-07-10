package com.example.gamestore.repository;

import com.example.gamestore.model.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.awt.print.Book;
import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

    @Query("SELECT p FROM Game p ")
    List<Game> getAllGame();

    List<Game> findAllByTitle(String title);
}
