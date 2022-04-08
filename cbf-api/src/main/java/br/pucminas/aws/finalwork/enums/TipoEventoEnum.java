package br.pucminas.aws.finalwork.enums;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(onlyExplicitlyIncluded = true)
public enum TipoEventoEnum {
    INICIO("inicio", "Partida entre %s e %s iniciou"),
    GOL("gol", "Novo gol na partida entre %s e %s"),
    INTERVALO("intervalo", "Hora do intervalo na partida entre %s e %s"),
    ACRESCIMO("acrescimo", "Acrescimo na partida entre %s e %s"),
    SUBSTITUICAO("substituicao", "Houve uma substituição na partida entre %s e %s"),
    ADVERTENCIA("advertencia", "Um jogador recebeu advertencia na partida entre %s e %s"),
    FIM("fim", "Fim da partida entre %s e %s");

    @ToString.Include
    private final String tipo;
    private final String descricao;

    TipoEventoEnum(String tipo, String descricao) {
        this.tipo = tipo;
        this.descricao = descricao;
    }

}