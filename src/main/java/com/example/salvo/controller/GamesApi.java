package com.example.salvo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@RequestMapping("/api")
public interface GamesApi {

    @GetMapping(path = "/games")
    Map<String, Object> games_view(Authentication auth);
}
