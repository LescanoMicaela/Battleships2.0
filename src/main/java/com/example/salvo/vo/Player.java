package com.example.salvo.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;


//@Entity tells Spring to create a player table for this class.
@Entity
@NoArgsConstructor
@Getter
@Setter
public class Player {

    //@Id says that the id instance variable holds the database key for this class
    // Id has a unique value for every instance
    @Id
    // @GeneratedValue and @GenericGenerator tell JPA to use whatever ID generator is provided by the database system
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    private String userName;
    private String password;

    @OneToMany(mappedBy = "player", fetch=FetchType.EAGER)
    Set<GamePlayer> gamePlayers = new HashSet<>();

    @OneToMany(mappedBy="player", fetch = FetchType.EAGER)
    Set<Score> scores = new LinkedHashSet<>();


    public Player(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public void addGamePlayers(GamePlayer gamePlayer){
        gamePlayer.setPlayer(this);
        gamePlayers.add(gamePlayer);
    }

    public void setId(long id) {
        this.id = id;
    }

    @JsonIgnore
    public Set<Score> getScores() {
        return scores;
    }


    public Score getScore(Game game){
        return this.getScores().stream().filter(s -> s.getGame().equals(game)).findFirst().orElse(null);
    }

}

