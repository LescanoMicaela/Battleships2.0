package com.example.salvo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@RequestMapping("/api")
public interface GameApi {

    @GetMapping("/game_view/{id}")
    Map<String, Object> game_view(@PathVariable Long id);}
