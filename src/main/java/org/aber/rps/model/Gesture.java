package org.aber.rps.model;

public enum Gesture {

    ROCK, PAPER, SCISSORS;

    public Result getResult(Gesture other) {
        int cmpResult = 0;

        if (!this.equals(other)) {
            switch (this) {
                case ROCK:
                    cmpResult = other.equals(PAPER) ? -1 : 1;
                    break;
                case PAPER:
                    cmpResult = other.equals(SCISSORS) ? -1 : 1;
                    break;
                case SCISSORS:
                    cmpResult = other.equals(ROCK) ? -1 : 1;
            }
        }

        return Result.get(cmpResult);
    }

}
