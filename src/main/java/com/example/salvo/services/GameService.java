package com.example.salvo.services;

import com.example.salvo.vo.Game;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface GameService {

    List<Game> listAll();

    Map<String, Object> makeGameDTO(Game game);
}
