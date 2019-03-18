package com.example.salvo.vo;

import com.example.salvo.vo.GamePlayer;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Ship {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    private String shipType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="gamePlayer_id")
    private GamePlayer gamePlayer;

    //used to create lists of embeddable objects.
    //one-to-many relationship between an entity and a simple piece of data.
    @ElementCollection
    @Column(name="shipLocation")
    List<String> shipLocation = new ArrayList<>();

    //That's what JPA will call to create new instances
    public Ship(){ }

    public Ship(String shipType,GamePlayer gamePlayer, List<String> shipLocation){
        this.shipType = shipType;
        this.gamePlayer = gamePlayer;
        this.shipLocation = shipLocation;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getShipType() {
        return shipType;
    }

    public void setShipType(String shipType) {
        this.shipType = shipType;
    }

    public GamePlayer getGamePlayer() {
        return gamePlayer;
    }

    public void setGamePlayer(GamePlayer gamePlayer) {
        this.gamePlayer = gamePlayer;
    }

    public List<String> getShipLocation() {
        return shipLocation;
    }

    public void setShipLocation(List<String> shipLocation) {
        this.shipLocation = shipLocation;
    }
}
