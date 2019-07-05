package org.aber.rps.model;

import java.util.Arrays;

public enum Result {
    WON(1), LOST(-1), DRAW(0);

    private int val;

    Result(int val) {
        this.val = val;
    }

    public static Result get(int val) {
        return Arrays.stream(values()).filter(result -> result.val == val).findFirst()
                .orElseThrow(() -> new RuntimeException("Unknown result"));
    }
}
