package com.example.gamestore;

import com.example.gamestore.model.dto.GameAddGameDto;
import com.example.gamestore.model.dto.UserLoginDto;
import com.example.gamestore.model.dto.UserRegisterDto;
import com.example.gamestore.service.GameService;
import com.example.gamestore.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    private final Scanner scanner;
    private final UserService userService;
    private final GameService gameService;

    public CommandLineRunnerImpl(UserService userService, GameService gameService) {
        this.userService = userService;
        this.gameService = gameService;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void run(String... args) throws Exception {

        while (true) {
            System.out.println("Enter commands");
            String[] commands = scanner.nextLine().split("\\|");
            switch (commands[0]) {

                case "RegisterUser" -> userService.registerUser(new UserRegisterDto(commands[1], commands[2],
                        commands[3], commands[4]));

                case "LoginUser" -> userService.loginUser(new UserLoginDto(commands[1],
                        commands[2]));

                case "Logout" -> userService.logOut();

                case "AddGame" -> gameService.addGame(new GameAddGameDto(commands[1], new BigDecimal(commands[2]),
                        Double.parseDouble(commands[3]), commands[4],commands[5], commands[6],
                        LocalDate.parse(commands[7], DateTimeFormatter.ofPattern("dd-MM-yyyy"))));
                        //String.valueOf(LocalDate.parse(commands[7], DateTimeFormatter.ofPattern("dd-MM-yyyy"))))

                case "EditGame" -> gameService.edinGame(Long.parseLong(commands[1]),
                        new BigDecimal(commands[2]),
                        Double.parseDouble(commands[3]));

                case "DeleteGame" -> gameService.
                        deleteGame(Long.parseLong(commands[1]));

                case "AllGames" -> gameService.printAllGames()
                        .forEach(System.out::println);

                case "DetailGame" -> gameService.detailGame(commands[1])
                        .forEach(System.out::println);
            }
        }

    }
}
