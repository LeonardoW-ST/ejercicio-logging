package com.softtek.ejercicioLogging.daos.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.Id;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

    public Bet(List<Integer> numbers) {
        this.numbers = numbers;
    }

}