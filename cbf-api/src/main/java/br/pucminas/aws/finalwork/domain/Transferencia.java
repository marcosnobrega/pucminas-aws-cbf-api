package br.pucminas.aws.finalwork.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "transferencia")
public class Transferencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "time_de_origem_id")
    private Time timeDeOrigem;

    @OneToOne
    @JoinColumn(name = "time_de_destino_id")
    private Time timeDeDestino;

    @Column(nullable = false)
    private LocalDate data;

    @Column(nullable = false)
    private Double valor;

    @OneToOne
    @JoinColumn(name = "jogador_id")
    private Jogador jogador;
}
