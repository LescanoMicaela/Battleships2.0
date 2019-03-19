package com.example.salvo.services;

import com.example.salvo.repositories.GameRepository;
import com.example.salvo.vo.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

@Service
public class GameServiceImpl implements GameService {

    private GameRepository gameRepository;
    private GamePlayerService gamePlayerService;

    @Autowired
    public GameServiceImpl(GameRepository gameRepository, GamePlayerService gamePlayerService){
        this.gameRepository = gameRepository;
        this.gamePlayerService = gamePlayerService;
    }

    @Override
    public List<Game> listAll(){
        List<Game> games = new ArrayList<>();
        gameRepository.findAll().forEach(games::add);
        return games;
    }

    @Override
    public Map<String, Object> makeGameDTO(Game game) {
        Map<String, Object> gameDTO = new LinkedHashMap<>();
        gameDTO.put("id", game.getId());
        gameDTO.put("created", game.getCreationDate());
        gameDTO.put("gameplayers", game.getGamePlayers().stream()
                .map(gp -> gamePlayerService.makeGamePlayerDTO(gp))
                .collect(toList()));
        return gameDTO;
    }


}
