package br.pucminas.aws.finalwork.repository;

import br.pucminas.aws.finalwork.domain.Evento;
import br.pucminas.aws.finalwork.domain.Partida;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventoRepository extends JpaRepository<Evento, Long> {
    List<Evento> findAllByPartida(Partida partida);
}
