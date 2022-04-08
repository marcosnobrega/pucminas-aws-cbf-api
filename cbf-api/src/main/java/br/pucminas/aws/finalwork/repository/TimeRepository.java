package br.pucminas.aws.finalwork.repository;

import br.pucminas.aws.finalwork.domain.Time;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeRepository extends JpaRepository<Time, Long> {
}
