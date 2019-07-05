package org.aber.rps.rest;

import org.aber.rps.model.Gesture;
import org.aber.rps.model.Player;
import org.aber.rps.model.Result;
import org.aber.rps.services.ResponseGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/game")
public class GameService {

    private final ResponseGenerator responseGenerator;

    @Autowired
    public GameService(ResponseGenerator responseGenerator) {
        this.responseGenerator = responseGenerator;
    }

    @GetMapping("/rps")
    @ResponseBody
    public String getGameResult(Player user) {
        Gesture userGesture = user.getGesture();
        Gesture computerGesture = responseGenerator.generateGesture();

        Result result = userGesture.getResult(computerGesture);

        return String.format("\nYour gesture: %s\nComputer gesture: %s\nResult: %s", userGesture, computerGesture, result);
    }

}
