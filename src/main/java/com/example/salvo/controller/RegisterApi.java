package com.example.salvo.controller;

import com.example.salvo.vo.Player;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api")
public interface RegisterApi {

    @PostMapping(path = "/players",produces ="application/json")
    ResponseEntity<Object> register(@RequestBody Player player);
}
