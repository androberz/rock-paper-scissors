package org.aber.rps;

import org.aber.rps.model.User;
import org.aber.rps.rest.GameService;
import org.aber.rps.rest.GameSessionImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Scanner;

import static org.aber.rps.rest.ErrorHandler.getErrorMessage;


@SpringBootApplication
public class Application {

    private static final String CONTINUE_MESSAGE = "Play again? [yes|no] or see statistics? [stats] ";

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext applicationContext) {
        return args -> {
            GameService gameService = applicationContext.getBean(GameService.class);
            gameService.setGameSession(new GameSessionImpl());

            try (Scanner cmdReader = new Scanner(System.in); User user = new User(cmdReader)) {
                String gameResult = gameService.getGameResult(user);
                System.out.println(gameResult);

                String decision;
                do {
                    System.out.println(CONTINUE_MESSAGE);

                    decision = cmdReader.nextLine().toUpperCase();

                    switch (decision) {
                        case "YES":
                        case "Y": {
                            gameResult = gameService.getGameResult(user);
                            System.out.println(gameResult);
                            break;
                        }
                        case "STATS": {
                            System.out.println(gameService.getGameStats());
                        }
                    }
                } while (!(decision.equals("NO") || decision.equals("N")));
            } catch (Exception ignore) {
                System.err.println(getErrorMessage());
            }
            System.exit(0);
        };
    }
}
