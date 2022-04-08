package br.pucminas.aws.finalwork.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JogadorDTO {

    @NotNull
    @NotBlank
    private String nome;

    @NotNull
    private LocalDate dataDeNascimento;

    @NotNull
    @NotBlank
    private String pais;

    @NotNull
    private Long idTime;
}
