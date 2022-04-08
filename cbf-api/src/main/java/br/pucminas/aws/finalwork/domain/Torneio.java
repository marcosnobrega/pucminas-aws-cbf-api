package br.pucminas.aws.finalwork.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "torneio")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Torneio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @ManyToMany
    @JoinTable(name = "torneio_time",
            joinColumns = {@JoinColumn(name = "torneio_id")},
            inverseJoinColumns = {@JoinColumn(name = "time_id")})
    private List<Time> times = new ArrayList<>();
}
