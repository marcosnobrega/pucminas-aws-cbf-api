package br.pucminas.aws.finalwork.service;

import br.pucminas.aws.finalwork.domain.Evento;
import br.pucminas.aws.finalwork.domain.Partida;
import br.pucminas.aws.finalwork.domain.Torneio;
import br.pucminas.aws.finalwork.enums.TipoEventoEnum;
import br.pucminas.aws.finalwork.repository.EventoRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EventoService {

    @Autowired
    private final EventoRepository repository;

    @Autowired
    private final PartidaService partidaService;

    @Autowired
    private final TorneioService torneioService;

    private Evento prepararEvento(TipoEventoEnum tipo, Partida partida) {
        Evento evento = new Evento();
        evento.setTipo(tipo.getTipo());
        evento.setDescricao(String.format(tipo.getDescricao(),
                partida.getTime1().getNome(), partida.getTime2().getNome()));
        evento.setPartida(partida);
        return evento;
    }

    private Evento registrarEvento(TipoEventoEnum tipo, Long torneioId, Long partidaId) {
        Torneio torneio = this.torneioService.getTorneio(torneioId);
        Partida partida = this.partidaService.getPartidaPorIdETorneioId(partidaId, torneio.getId());
        Evento evento = this.prepararEvento(tipo, partida);
        return this.repository.save(evento);
    }

    public Evento registrarInicio(Long torneioId, Long partidaId) {
        return this.registrarEvento(TipoEventoEnum.INICIO, torneioId, partidaId);
    }

    public Evento registrarGol(Long torneioId, Long partidaId) {
        return this.registrarEvento(TipoEventoEnum.GOL, torneioId, partidaId);
    }

    public Evento registrarIntervalo(Long torneioId, Long partidaId) {
        return this.registrarEvento(TipoEventoEnum.INTERVALO, torneioId, partidaId);
    }

    public Evento registrarAcrescimo(Long torneioId, Long partidaId) {
        return this.registrarEvento(TipoEventoEnum.ACRESCIMO, torneioId, partidaId);
    }

    public Evento registrarSubstituicao(Long torneioId, Long partidaId) {
        return this.registrarEvento(TipoEventoEnum.SUBSTITUICAO, torneioId, partidaId);
    }

    public Evento registrarAdvertencia(Long torneioId, Long partidaId) {
        return this.registrarEvento(TipoEventoEnum.ADVERTENCIA, torneioId, partidaId);
    }

    public Evento registrarFim(Long torneioId, Long partidaId) {
        return this.registrarEvento(TipoEventoEnum.FIM, torneioId, partidaId);
    }

    public List<Evento> listarEventosDaPartida(Long torneioId, Long partidaId) {
        Torneio torneio = this.torneioService.getTorneio(torneioId);
        Partida partida = this.partidaService.getPartidaPorIdETorneioId(partidaId, torneio.getId());
        return this.repository.findAllByPartida(partida);
    }
}
