package com.example.salvo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;


//@Entity tells Spring to create a player table for this class.
    @Entity
    public class Player {

        //@Id says that the id instance variable holds the database key for this class
        // Id has a unique value for every instance
        @Id
        // @GeneratedValue and @GenericGenerator tell JPA to use whatever ID generator is provided by the database system
        @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
        @GenericGenerator(name = "native", strategy = "native")
        private long id;
        private String userName;

        @OneToMany(mappedBy = "player", fetch=FetchType.EAGER)
        Set<GamePlayer> gamePlayers = new HashSet<>();

        //That's what JPA will call to create new instances
        public Player() { }

        public Player(String userName) {
            this.userName = userName;
        }

        public long getId() {
            return id;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public void addGamePlayers(GamePlayer gamePlayer){
            gamePlayer.setPlayer(this);
            gamePlayers.add(gamePlayer);
        }


        public List<Game> getGames(){
            return gamePlayers.stream().map( gp -> gp.getGame()).collect(toList());
        }

        public Set<GamePlayer> getGamePlayers() {
        return gamePlayers;
        }


        public void setGamePlayers(Set<GamePlayer> gamePlayers) {
        this.gamePlayers = gamePlayers;
        }
    }

