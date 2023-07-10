package com.example.gamestore.service.impl;

import com.example.gamestore.model.dto.GameAddGameDto;
import com.example.gamestore.model.entity.Game;
import com.example.gamestore.repository.GameRepository;
import com.example.gamestore.service.GameService;
import com.example.gamestore.util.ValidationUtil;
import jakarta.validation.ConstraintViolation;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;


    public GameServiceImpl(GameRepository gameRepository, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.gameRepository = gameRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public void addGame(GameAddGameDto gameAddGameDto) {

        Set<ConstraintViolation<GameAddGameDto>> violations = validationUtil.violation(gameAddGameDto);

        if (!violations.isEmpty()) {
            violations.stream().map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);
            return;
        }

        Game game = modelMapper.map(gameAddGameDto, Game.class);

        gameRepository.save(game);
        System.out.println("Game is added");
    }

    @Override
    public void edinGame(long gameId, BigDecimal price, double size) {
        Game game = gameRepository.findById(gameId)
                .orElse(null);

        if (game == null) {
            System.out.println("Game not exist");
            return;
        }

        game.setPrice(price);
        game.setSize(size);

        gameRepository.save(game);
        System.out.println("Game was edited");
    }

    @Override
    public void deleteGame(long gameId) {
        Game game = gameRepository.findById(gameId)
                .orElse(null);

        if (game == null) {
            System.out.println("Game not exist");
            return;
        }

        gameRepository.delete(game);
        System.out.println("Game was deleted");
    }

    @Override
    public List<String> printAllGames() {
        return gameRepository.getAllGame()
                .stream()
                .map(game -> String.format("%s %s", game.getTitle(),
                        game.getPrice()))
                .collect(Collectors.toList());
    }

    @Override
    public List<String> detailGame(String gameTitle) {
        return gameRepository.findAllByTitle(gameTitle)
                .stream()
                .map(game -> {
                    return String.format(("Title: %s%n" +
                                    "Price: %.2f%n" +
                                    "Description: %s%n" +
                                    "Release date: %s"), game.getTitle(), game.getPrice(),
                            game.getDescription(), game.getReleaseDate());
                })
                .collect(Collectors.toList());
    }
}
