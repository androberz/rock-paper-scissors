package org.aber.rps.rest;

import org.aber.rps.model.Gesture;

import java.util.Optional;

public interface GameSession {

    void addResult(GameSessionImpl.GameResult gameResult);

    String getStats();

    Optional<Gesture> getPrevComputerGesture();

}
