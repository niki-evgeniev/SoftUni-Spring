package com.example.gamestore.service;

import com.example.gamestore.model.dto.GameAddGameDto;
import com.example.gamestore.model.entity.Game;

import java.awt.print.Book;
import java.math.BigDecimal;
import java.util.List;

public interface GameService {

    void addGame(GameAddGameDto gameAddGameDto);

    void edinGame(long gameId, BigDecimal price, double size);

    void deleteGame(long gameId);

    List<String> printAllGames();

    List<String> detailGame(String gameTitle);
}
