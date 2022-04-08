package br.pucminas.aws.finalwork.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "partida")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Partida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime data;

    @ManyToOne(optional = false)
    private Time time1;

    @ManyToOne(optional = false)
    private Time time2;

    @ManyToOne(optional = false)
    private Torneio torneio;
}
