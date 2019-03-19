package com.example.salvo.controller;

import com.example.salvo.services.GamePlayerService;
import com.example.salvo.vo.Game;
import com.example.salvo.vo.GamePlayer;
import com.example.salvo.vo.Salvo;
import com.example.salvo.vo.Ship;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.util.stream.Collectors.toList;

@RestController
public class GameController implements GameApi {

    @Autowired
    GamePlayerService gamePlayerService;

    @Override
    public Map<String, Object> game_view(@PathVariable Long id) {
        GamePlayer gamePlayer = gamePlayerService.findGamePlayerById(id);
        Game game = gamePlayer.getGame();
        Set<GamePlayer> gamePlayerSet = game.getGamePlayers();
        Map<String, Object> dto = new LinkedHashMap<>();
        dto.put("id", gamePlayer.getId());
        dto.put("created", gamePlayer.getDate());
        dto.put("gamePlayers", gamePlayerSet.stream()
                .map(gp -> gamePlayerService.makeGamePlayerDTO(gp))
                .collect(toList()));
        dto.put("ships", gamePlayer.getShips().stream()
                .map(s -> makeShipDTO(s))
                .collect(toList()));
        dto.put("salvoes", gamePlayerSet.stream()
                .map(gp -> makeSalvoesDTO(gp.getSalvoes()))
                .collect(toList()));
        return dto;
    }


    public Map<String, Object> makeShipDTO(Ship ship) {
        Map<String, Object> dto = new LinkedHashMap<>();
        dto.put("type", ship.getShipType());
        dto.put("location", ship.getShipLocation());
        return dto;
    }

    private Map<String, Object> makeSalvoDTO(Salvo salvo) {
        Map<String, Object> dto = new LinkedHashMap<>();
        dto.put("turn", salvo.getTurn());
        dto.put("location", salvo.getSalvoLocation());
        dto.put("player", salvo.getGamePlayer().getId());
        return dto;
    }

    private List<Object> makeSalvoesDTO(Set<Salvo> salvoes) {
        return salvoes.stream().map(salvo -> makeSalvoDTO(salvo))
                .collect(toList());

    }

}
