package br.pucminas.aws.finalwork.repository;

import br.pucminas.aws.finalwork.domain.Jogador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JogadorRepository extends JpaRepository<Jogador, Long> {
}
