package com.example.salvo.services;

import com.example.salvo.vo.GamePlayer;

import java.util.HashMap;
import java.util.Map;

public interface GamePlayerService {

    GamePlayer findGamePlayerById(Long id);


    Map<String, Object> makeGamePlayerDTO(GamePlayer gamePlayer);
}
