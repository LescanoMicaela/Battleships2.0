package com.example.salvo.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
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

    public Ship(String shipType,GamePlayer gamePlayer, List<String> shipLocation){
        this.shipType = shipType;
        this.gamePlayer = gamePlayer;
        this.shipLocation = shipLocation;
    }

}
