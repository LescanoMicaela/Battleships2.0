package com.example.salvo.controller;

import com.example.salvo.services.GameService;
import com.example.salvo.services.PlayerService;
import com.example.salvo.vo.Game;
import com.example.salvo.vo.GamePlayer;
import com.example.salvo.vo.Player;
import com.example.salvo.vo.Score;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import static java.util.stream.Collectors.toList;

@RestController
public class GamesController implements GamesApi {

    @Autowired
    private GameService gameService;

    @Autowired
    PlayerService playerService;


    @Override
    public Map<String, Object> games_view(Authentication auth) {
        Map<String, Object> gamesDTO = new HashMap<>();
        gamesDTO.put("games", gameService.listAll()
                .stream()
                .map(g -> gameService.makeGameDTO(g)).collect(toList()));
        gamesDTO.put("leaderboard", playerService.listAll()
                .stream()
                .map(p -> playerService.makeLeaderboradDTO(p))
                .collect(toList()));
        if (auth != null) {
            gamesDTO.put("currentUser", playerService.makePlayerDTO(playerService.getCurrentUser(auth)));
        } else {
            gamesDTO.put("currentUser", null);
        }
        return gamesDTO;

    }


}
