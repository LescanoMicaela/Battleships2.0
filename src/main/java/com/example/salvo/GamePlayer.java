package com.example.salvo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

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




}
