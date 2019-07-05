package org.aber.rps.services;

import org.aber.rps.model.Gesture;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class ComputerResponseGenerator implements ResponseGenerator {

    private final Random randomGenerator = new Random();

    @Override
    public Gesture generateGesture() {
        return Gesture.values()[randomGenerator.nextInt(Gesture.values().length)];
    }

    @Override
    public Gesture generateNextGesture(Gesture prev) {
        return randomGenerator.nextInt(2) == 0 ? prev : generateGesture();
    }
}
