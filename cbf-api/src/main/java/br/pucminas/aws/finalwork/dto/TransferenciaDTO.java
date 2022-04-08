package br.pucminas.aws.finalwork.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class TransferenciaDTO {

    @NotNull
    private Long idTimeDeDestino;

    @NotNull
    private LocalDate data;

    @NotNull
    private Double valor;

    @NotNull
    private Long idJogador;
}
