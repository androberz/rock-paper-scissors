package org.aber.rps.model;

public class WebUser implements Player {

    private String input;

    public WebUser(String input) {
        this.input = input;
    }

    @Override
    public Gesture getGesture() {
        return Gesture.valueOf(input.toUpperCase());
    }
}
