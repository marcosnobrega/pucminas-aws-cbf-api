package br.pucminas.aws.finalwork.repository;

import br.pucminas.aws.finalwork.domain.Transferencia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferenciaRepository extends JpaRepository<Transferencia, Long> {
}
