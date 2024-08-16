package com.broadcom.demo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "matches")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "winner_id", referencedColumnName = "id", nullable = false)
    private Player winner;

    @ManyToOne
    @JoinColumn(name = "loser_id", referencedColumnName = "id", nullable = false)
    private Player loser;
}
