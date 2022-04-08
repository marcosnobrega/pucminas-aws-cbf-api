package br.pucminas.aws.finalwork.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class TimeDTO {

    @NotNull
    @NotBlank
    private String nome;

    @NotNull
    @NotBlank
    private String localidade;

}
