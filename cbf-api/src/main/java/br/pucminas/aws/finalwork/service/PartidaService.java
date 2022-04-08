package br.pucminas.aws.finalwork.service;

import br.pucminas.aws.finalwork.domain.Partida;
import br.pucminas.aws.finalwork.domain.Torneio;
import br.pucminas.aws.finalwork.dto.PartidaDTO;
import br.pucminas.aws.finalwork.repository.PartidaRepository;
import br.pucminas.aws.finalwork.repository.TorneioRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class PartidaService {

    @Autowired
    private final PartidaRepository repository;

    @Autowired
    private final TimeService timeService;

    @Autowired
    private final TorneioRepository torneioRepository;

    public List<Partida> listarPorTorneioId(Long torneioId) {
        return this.repository.findAllByTorneioId(torneioId);
    }

    public Partida adicionarPartida(Torneio torneio, PartidaDTO dto) {
        Partida partida = new Partida();
        partida.setData(dto.getData());
        partida.setTime1(this.timeService.getTime(dto.getIdTime1()));
        partida.setTime2(this.timeService.getTime(dto.getIdTime2()));
        partida.setTorneio(torneio);
        return this.repository.save(partida);
    }

    public Partida getPartidaPorIdETorneioId(Long partidaId, Long torneioId) {
        return this.repository.findByIdAndTorneioId(partidaId, torneioId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Partida atualizarPartida(Torneio torneio, Long partidaId, PartidaDTO dto) {
        Partida partida = this.getPartidaPorIdETorneioId(partidaId, torneio.getId());
        partida.setData(dto.getData());
        partida.setTime1(this.timeService.getTime(dto.getIdTime1()));
        partida.setTime2(this.timeService.getTime(dto.getIdTime2()));
        partida.setTorneio(torneio);
        return this.repository.save(partida);
    }

    public void deletarPartida(Torneio torneio, Long partidaId) {
        Partida partida = this.getPartidaPorIdETorneioId(partidaId, torneio.getId());
        this.repository.delete(partida);
    }


}
