package com.example.salvo.controller;

import com.example.salvo.repositories.GamePlayerRepository;
import com.example.salvo.repositories.GameRepository;
import com.example.salvo.repositories.PlayerRepository;
import com.example.salvo.services.GameService;
import com.example.salvo.services.PlayerService;
import com.example.salvo.services.PlayerServiceImpl;
import com.example.salvo.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static java.util.stream.Collectors.toList;

// A dispatcher is code that examines the URL sent by a client and decides what method of what class to pass the URL.
//Spring creates a dispatcher, using patterns collected from the classes you define and annotations you write.
// a RestRepository, such as Person, creates a large number of URL patterns, such as /persons, /persons/{id}, /persons/search, and so on
//@RequestMapping("/games") on a method adds that URL to the dispatcher ( to use distinct prefixes for the data URLs.)

//converting this java data into a string using JavaScript Object Notation (JSON)
@RestController
public class SalvoController implements SalvoApi {

    private GamePlayerRepository gamePlayerRepository;
    private PlayerRepository playerRepository;
    private PasswordEncoder passwordEncoder;
    private PlayerService playerService;

    @Autowired
    public SalvoController(PasswordEncoder passwordEncoder, PlayerRepository playerRepository,
                           GamePlayerRepository gamePlayerRepository, PlayerService playerService) {
        this.passwordEncoder = passwordEncoder;
        this.playerRepository = playerRepository;
        this.gamePlayerRepository = gamePlayerRepository;
        this.playerService = playerService;

    }



    //This method will create a JSON to create a game view.
    // The @RequestParam annotation says that the URL will include ?id=value




//    //Method to return null if no one is logged in, instead of anonymousUser
//    private boolean isGuest(Authentication authentication) {
//        return authentication == null || authentication instanceof AnonymousAuthenticationToken;
//    }
}
