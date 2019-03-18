package com.example.salvo.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
public class GamePlayer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    private Date date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="player_id")
    private Player player;

    @ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn(name="game_id")
    private Game game;

    @OneToMany(mappedBy = "gamePlayer" , fetch = FetchType.EAGER)
    Set<Ship> ships = new LinkedHashSet<>();

    @OneToMany(mappedBy="gamePlayer", fetch = FetchType.EAGER)
    Set<Salvo> salvoes = new HashSet<>();


    public GamePlayer(){

    }

    public GamePlayer(Player player, Game game){
        this.player = player;
        this.game = game;
        this.date = new Date();
    }

    @JsonIgnore
    public Player getPlayer(){
        return this.player;
    }

    public void setPlayer(Player player){
        this.player = player;
    }


    public long getId(){
        return this.id;
    }
    @JsonIgnore
    public Game getGame(){
        return this.game;
    }

    public void setGame(Game game){
        this.game = game;
    }


    public Date getDate(){
        return this.date;
    }

    public void setDate(Date date){
        this.date = date;
    }

    @JsonIgnore
    public Set<Ship> getShips() {
        return ships;
    }

    public void setShips(Set<Ship> ships) {
        this.ships = ships;
    }

    public void addShip(Ship ship){
        ship.setGamePlayer(this);
        this.ships.add(ship);
    }

    public void setId(long id) {
        this.id = id;
    }

    @JsonIgnore
    public Set<Salvo> getSalvoes() {
        return salvoes;
    }

    public void setSalvoes(Set<Salvo> salvoes) {
        this.salvoes = salvoes;
    }

//    public Score getScore(){
//        return this.getPlayer().getScores().stream().filter(s -> s.getGame().equals(this.getGame())).findFirst().orElse(null);
//
//    }

    @JsonIgnore
    public Score getScore() {
       return this.player.getScore(this.game);
    }
}
