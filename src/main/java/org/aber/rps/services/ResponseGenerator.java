package org.aber.rps.services;

import org.aber.rps.model.Gesture;

public interface ResponseGenerator {

    Gesture generateGesture();

    // Try to exploit that humans are very bad at generating random numbers
    // Make less entropy
    Gesture generateNextGesture(Gesture prev);

}
