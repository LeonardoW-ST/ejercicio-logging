package com.softtek.ejercicioLogging.daos.model;

import jakarta.persistence.*;
import org.apache.catalina.User;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "bets")
public class Bet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ElementCollection
    @CollectionTable(
            name = "bet_numbers",
            joinColumns = @JoinColumn(name = "bet_id")
    )
    @Column(name = "number", nullable = false)
    @OrderColumn(name = "position")
    private List<Integer> numbers = new ArrayList<>();
}