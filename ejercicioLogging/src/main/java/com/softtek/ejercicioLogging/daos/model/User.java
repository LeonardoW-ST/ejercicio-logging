package com.softtek.ejercicioLogging.daos.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {

    @Id
    private String id;

    @Column(name="name")
    private String nombre;

    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Bet> bets = new ArrayList<>();


    public User(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public User(String id, String nombre, List<Bet> bets) {
        this.id = id;
        this.nombre = nombre;
        this.bets = bets;
    }

    public void addBet(Bet bet) {
        this.bets.add(bet);
        bet.setUser(this);
    }

    public void removeBet(Bet bet) {
        this.bets.remove(bet);
        bet.setUser(null);
    }


}
