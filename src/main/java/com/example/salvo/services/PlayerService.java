package com.example.salvo.services;

import com.example.salvo.vo.Player;
import com.example.salvo.vo.Salvo;
import com.example.salvo.vo.Ship;
import org.springframework.security.core.Authentication;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.util.stream.Collectors.toList;

public interface PlayerService {

    List<Player> listAll();

    Player saveOrUpdate(Player player);

    Player findByUserName(String userName);

    Boolean checkForEmptyFields (Player player);

    Player getCurrentUser(Authentication auth);

    Map<String, Object> makePlayerDTO(Player player);

    Map<String, Object> makeLeaderboradDTO(Player player);


}
