package com.example.salvo.services;

import com.example.salvo.repositories.PlayerRepository;
import com.example.salvo.vo.Player;
import com.example.salvo.vo.Score;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

@Service
public class PlayerServiceImpl implements PlayerService {
    private PlayerRepository playerRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public PlayerServiceImpl(PlayerRepository playerRepository, PasswordEncoder passwordEncoder){
        this.playerRepository = playerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<Player> listAll(){
        List<Player> players = new ArrayList<>();
        playerRepository.findAll().forEach(players::add);
        return players;
    }

    @Override
    public Player saveOrUpdate(Player player){
        playerRepository.save(new Player(player.getUserName(), passwordEncoder.encode(player.getPassword())));
        return player;
    }

    @Override
    public Player findByUserName(String userName){
        return playerRepository.findByUserName(userName);
    }

    @Override
    public Boolean checkForEmptyFields(Player player){
        if (StringUtils.isEmpty(player.getUserName()) || StringUtils.isEmpty(player.getPassword()) ){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Player getCurrentUser(Authentication auth) {
        return playerRepository.findByUserName(auth.getName());
    }

    @Override
    public Map<String, Object> makePlayerDTO(Player player) {
        Map<String, Object> playerDTO = new HashMap<>();
        playerDTO.put("id", player.getId());
        playerDTO.put("email", player.getUserName());
        return playerDTO;
    }

    @Override
    public Map<String, Object> makeLeaderboradDTO(Player player) {
        Map<String, Object> dto = new LinkedHashMap<>();
        dto.put("player", player.getUserName());
        dto.put("scores", countPlayerScores(player.getScores()));
        return dto;
    }




    private Map<String, Double> countPlayerScores(Set<Score> scores) {
        Map<String, Double> counter = new LinkedHashMap<>();
        Double total = 0.0;
        for (Score score : scores) {
            total += score.getScore();
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


    private String getScoreStatus(Double score) {
        String scoreStatus = "";
        if (score == 0.0) {
            scoreStatus = "lose";
        } else if (score == 0.5) {
            scoreStatus = "tie";
        } else if (score == 1.0) {
            scoreStatus = "win";
        }
        return scoreStatus;
    }


}
