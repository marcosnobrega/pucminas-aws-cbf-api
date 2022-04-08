package br.pucminas.aws.finalwork.repository;

import br.pucminas.aws.finalwork.domain.Partida;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PartidaRepository extends JpaRepository<Partida, Long> {

    List<Partida> findAllByTorneioId(Long torneioId);

    Optional<Partida> findByIdAndTorneioId(Long id, Long torneioId);

}
