package org.aber.rps.model

import spock.lang.Specification

class GestureTest extends Specification {

    def "test getResult() for gestures"() {
        when:
        def result = gesture1.getResult(gesture2)
        then:
        result == expectedResult
        where:
        gesture1         | gesture2         | expectedResult
        Gesture.ROCK     | Gesture.ROCK     | Result.DRAW
        Gesture.ROCK     | Gesture.SCISSORS | Result.WON
        Gesture.ROCK     | Gesture.PAPER    | Result.LOST
        Gesture.PAPER    | Gesture.PAPER    | Result.DRAW
        Gesture.PAPER    | Gesture.ROCK     | Result.WON
        Gesture.PAPER    | Gesture.SCISSORS | Result.LOST
        Gesture.SCISSORS | Gesture.SCISSORS | Result.DRAW
        Gesture.SCISSORS | Gesture.PAPER    | Result.WON
        Gesture.SCISSORS | Gesture.ROCK     | Result.LOST
    }
}
