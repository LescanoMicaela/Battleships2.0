package com.example.salvo.services;

import com.example.salvo.repositories.GamePlayerRepository;
import com.example.salvo.vo.GamePlayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class GamePlayerImpl implements GamePlayerService {

    @Autowired
    PlayerService playerService;
    @Autowired
    GamePlayerRepository gamePlayerRepository;

    @Override
    public GamePlayer findGamePlayerById(Long id){
        return gamePlayerRepository.findById(id).orElse(null);
    }

    @Override
    public Map<String, Object> makeGamePlayerDTO(GamePlayer gamePlayer) {
        Map<String, Object> gamePlayerDTO = new HashMap<>();
        gamePlayerDTO.put("id", gamePlayer.getId());
        gamePlayerDTO.put("player", playerService.makePlayerDTO(gamePlayer.getPlayer()));
        return gamePlayerDTO;
    }


}
