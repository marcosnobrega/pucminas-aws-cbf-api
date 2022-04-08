package br.pucminas.aws.finalwork.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class PartidaDTO {

    @NotNull
    private LocalDateTime data;

    @NotNull
    private Long idTime1;

    @NotNull
    private Long idTime2;

}
