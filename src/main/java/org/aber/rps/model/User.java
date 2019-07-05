package org.aber.rps.model;

import org.aber.rps.rest.ErrorHandler;

import java.util.Scanner;

public class User implements Player, AutoCloseable {

    private final Scanner scanner;

    public User(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public Gesture getGesture() {
        System.out.println("Rock, Paper, Scissors ...");
        try {
            return Gesture.valueOf(scanner.nextLine().toUpperCase());
        } catch (Exception ignored) {
            System.err.println(ErrorHandler.getWrongInputMessage());

            return getGesture();
        }
    }

    @Override
    public void close() {
        scanner.close();
    }
}
