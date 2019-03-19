package com.example.salvo.controller;

import com.example.salvo.services.PlayerService;
import com.example.salvo.vo.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController implements RegisterApi {

    private PlayerService playerService;

    @Autowired
    public RegisterController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @Override
    public ResponseEntity<Object> register(@RequestBody Player player) {
        if (playerService.checkForEmptyFields(player)) {
            return new ResponseEntity<>("Missing data", HttpStatus.FORBIDDEN);
        }
        if (playerService.findByUserName(player.getUserName()) != null) {
            return new ResponseEntity<>("Name already in use", HttpStatus.FORBIDDEN);
        }
        playerService.saveOrUpdate(player);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
