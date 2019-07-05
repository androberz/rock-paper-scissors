package org.aber.rps.rest

import org.aber.rps.model.Gesture
import org.aber.rps.model.Result
import spock.lang.Specification


class GameSessionTest extends Specification {

    def "test getting game statistics when no games"() {
        given:
        def gameSession = new GameSessionImpl()
        when:
        def stats = gameSession.getStats()
        then:
        stats == "\nNo games played\n"
    }

    def "test getting game statistics"() {
        given:
        def gameSession = new GameSessionImpl()
        gameSession.addResult(new GameSessionImpl.GameResult(Gesture.ROCK, Gesture.ROCK, Result.DRAW))
        gameSession.addResult(new GameSessionImpl.GameResult(Gesture.ROCK, Gesture.PAPER, Result.LOST))
        gameSession.addResult(new GameSessionImpl.GameResult(Gesture.ROCK, Gesture.SCISSORS, Result.WON))
        when:
        def stats = gameSession.getStats()
        then:
        stats == "User: ROCK, Computer: ROCK, Result: DRAW\nUser: ROCK, Computer: PAPER, Result: LOST\nUser: ROCK, Computer: SCISSORS, Result: WON\nWins: 33.33, Looses: 33.33, Draws: 33.33"
    }
}
