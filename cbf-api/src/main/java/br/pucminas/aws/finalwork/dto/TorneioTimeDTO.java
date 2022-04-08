package br.pucminas.aws.finalwork.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TorneioTimeDTO {

    @NotNull
    private Long timeId;
}
