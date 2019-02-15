package com.example.salvo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.LinkedCaseInsensitiveMap;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

// A dispatcher is code that examines the URL sent by a client and decides what method of what class to pass the URL.
//Spring creates a dispatcher, using patterns collected from the classes you define and annotations you write.
// a RestRepository, such as Person, creates a large number of URL patterns, such as /persons, /persons/{id}, /persons/search, and so on
//@RequestMapping("/games") on a method adds that URL to the dispatcher ( to use distinct prefixes for the data URLs.)


//converting this java data into a string using JavaScript Object Notation (JSON)
@RestController
//when we do a request to /api we will get the method we determine
@RequestMapping("/api")
//Mapping to api prevent any accidental overlap with the REST repository URLs, because we've made them start with /rest.
public class SalvoController {

    //@Autowired tells Spring to automatically create an instance of GameRepository and store it in the instance variable gameRepository
    // This technique is called dependency injection.
    // We let the framework create and "inject" the objects we need, rather than writing a call to the new operator
    @Autowired
    GameRepository gameRepository;
    @Autowired
    GamePlayerRepository gamePlayerRepository;
    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping(path = "/players", method = RequestMethod.POST)
    public ResponseEntity<Object> register(@RequestParam String email, @RequestParam String password) {

        if ( email.isEmpty() || password.isEmpty()) {
            return new ResponseEntity<>("Missing data", HttpStatus.FORBIDDEN);
        }

        if (playerRepository.findByUserName(email) !=  null) {
            return new ResponseEntity<>("Name already in use", HttpStatus.FORBIDDEN);
        }

        playerRepository.save(new Player(email, passwordEncoder.encode(password)));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping("/games")
    public Map<String,Object> games_view(){
        Map<String,Object> gamesDTO = new HashMap<>();
        //repo.findAll() return a list of all instances in this repo
        gamesDTO.put("games",gameRepository.findAll()
                .stream()
                .map(g -> makeGameDTO(g)).collect(toList()) );
        gamesDTO.put("leaderboard",playerRepository.findAll()
                    .stream()
                    .map(p -> makeLeaderboradDTO(p))
                    .collect(toList()));
        return gamesDTO;

    }

    public Map<String,Object> makeGameDTO(Game game){
        Map<String,Object> gameDTO = new LinkedHashMap<>();
        gameDTO.put("id", game.getId());
        gameDTO.put("created", game.getCreationDate());
        gameDTO.put("gameplayers",game.getGamePlayers().stream()
                                        .map(gp -> makeGamePlayerDTO(gp))
                                        .collect(toList()));
        return gameDTO;
    }


    public String getScoreStatus(Double score){
        String scoreStatus ="";
        if (score == 0.0){
            scoreStatus = "lose";
        } else if(score == 0.5){
            scoreStatus = "tie";
        }else if(score == 1.0){
            scoreStatus = "win";
        }
        return scoreStatus;
    }


    public Map<String,Double> countPlayerScores(Set<Score> scores){
        Map<String,Double> counter = new LinkedHashMap<>();
        Double total = 0.0;
        for (Score score : scores) {
            total+= score.getScore();
            if (counter.containsKey(getScoreStatus(score.getScore()))) {
                Double value = counter.get(getScoreStatus(score.getScore()));
                value++;
                counter.put(getScoreStatus(score.getScore()), value);
            } else {
                counter.put(getScoreStatus(score.getScore()), 1.0);
            }
            counter.put("total", total);
        }
        return counter;
    }

    public Map<String,Object> makeLeaderboradDTO(Player player){
        Map<String,Object> dto = new LinkedHashMap<>();
        dto.put("player",player.getUserName());
        dto.put("scores", countPlayerScores(player.getScores()));
        return dto;
    }


    public Map<String,Object> makePlayerDTO(Player player){
        Map<String,Object> playerDTO = new HashMap<>();
        playerDTO.put("id", player.getId());
        playerDTO.put("email", player.getUserName());
        return playerDTO;
    }

    public Map<String,Object> makeGamePlayerDTO(GamePlayer gamePlayer){
        Map<String,Object> gamePlayerDTO = new HashMap<>();
        gamePlayerDTO.put("id",gamePlayer.getId());
        gamePlayerDTO.put("player",makePlayerDTO(gamePlayer.getPlayer()));
        return gamePlayerDTO;
    }

    //This method will create a JSON to create a game view.
    // The @RequestParam annotation says that the URL will include ?id=value

    @RequestMapping("/game_view/{id}")
    public Map<String, Object> game_view(@PathVariable Long id){
        ///---------- '1.5.10.RELEASE'---------------- ////
//        GamePlayer gamePlayer = gamePlayerRepository.findOne(id);

        ////----------'2.1.1.RELEASE' ---------------////
        GamePlayer gamePlayer = gamePlayerRepository.findById(id).orElse(null);
        Game game = gamePlayer.getGame();
        Set<GamePlayer> gamePlayerSet = game.getGamePlayers();
        Map<String,Object> dto = new LinkedHashMap<>();
        dto.put("id",gamePlayer.getId());
        dto.put("created",gamePlayer.getDate());
        dto.put("gamePlayers", gamePlayerSet.stream()
                                            .map( gp -> makeGamePlayerDTO(gp))
                                            .collect(toList()));
        dto.put("ships",gamePlayer.getShips().stream()
                                            .map(s-> makeShipDTO(s))
                                            .collect(toList()));
        dto.put("salvoes", gamePlayerSet.stream()
                                        .map(gp -> makeSalvoesDTO(gp.getSalvoes()))
                                        .collect(toList()));
        return dto;
    }

    public Map<String,Object> makeShipDTO(Ship ship){
        Map<String,Object> dto = new LinkedHashMap<>();
        dto.put("type", ship.getShipType());
        dto.put("location",ship.getShipLocation());
        return dto;
    }

    private Map<String,Object> makeSalvoDTO(Salvo salvo){
        Map<String,Object> dto = new LinkedHashMap<>();
        dto.put("turn",salvo.getTurn());
        dto.put("location", salvo.getSalvoLocation());
        dto.put("player",salvo.getGamePlayer().getId());
        return dto;
    }

    private List<Object> makeSalvoesDTO(Set<Salvo> salvoes){
            return salvoes.stream().map(salvo -> makeSalvoDTO(salvo))
                    .collect(toList());

    }
}
