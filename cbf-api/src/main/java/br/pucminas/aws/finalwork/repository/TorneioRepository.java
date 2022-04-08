package br.pucminas.aws.finalwork.repository;

import br.pucminas.aws.finalwork.domain.Torneio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TorneioRepository extends JpaRepository<Torneio, Long> {
}
