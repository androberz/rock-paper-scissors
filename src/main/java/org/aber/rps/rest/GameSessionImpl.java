package org.aber.rps.rest;

import org.aber.rps.model.Gesture;
import org.aber.rps.model.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.INTERFACES)
public class GameSessionImpl implements GameSession {

    private List<GameResult> gameResults = new ArrayList<>();

    @Override
    public void addResult(GameResult gameResult) {
        gameResults.add(gameResult);
    }

    @Override
    public String getStats() {
        StringBuilder sb = new StringBuilder();

        if (!gameResults.isEmpty()) {
            String gameResults = this.gameResults.stream().map(gameResult ->
                    String.format("User: %s, Computer: %s, Result: %s", gameResult.getUserGesture().name(),
                            gameResult.getComputerGesture().name(), gameResult.getResult().name()))
                    .collect(Collectors.joining("\n"));

            sb.append(gameResults);

            sb.append(String.format("\nWins: %.2f, Looses: %.2f, Draws: %.2f",
                    getPercentageOf(Result.WON),
                    getPercentageOf(Result.LOST),
                    getPercentageOf(Result.DRAW)));
        } else {
            sb.append("\nNo games played\n");
        }

        return sb.toString();
    }

    @Override
    public Optional<Gesture> getPrevComputerGesture() {
        if (gameResults.isEmpty()) {
            return Optional.empty();
        }

        return gameResults.stream().skip(gameResults.size() - 1).findFirst().map(GameResult::getComputerGesture);
    }

    private double getPercentageOf(Result result) {
        long resultsCount = gameResults.stream().filter(gameResult -> gameResult.getResult().equals(result)).count();

        return resultsCount / (double) gameResults.size() * 100;
    }

    static class GameResult {
        private Gesture userGesture;
        private Gesture computerGesture;
        private Result result;

        GameResult(Gesture userGesture, Gesture computerGesture, Result result) {
            this.userGesture = userGesture;
            this.computerGesture = computerGesture;
            this.result = result;
        }

        Gesture getUserGesture() {
            return userGesture;
        }

        Gesture getComputerGesture() {
            return computerGesture;
        }

        Result getResult() {
            return result;
        }
    }
}
