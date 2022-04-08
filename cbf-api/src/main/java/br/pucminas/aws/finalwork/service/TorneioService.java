package br.pucminas.aws.finalwork.service;

import br.pucminas.aws.finalwork.domain.Partida;
import br.pucminas.aws.finalwork.domain.Time;
import br.pucminas.aws.finalwork.domain.Torneio;
import br.pucminas.aws.finalwork.dto.PartidaDTO;
import br.pucminas.aws.finalwork.dto.TorneioDTO;
import br.pucminas.aws.finalwork.dto.TorneioTimeDTO;
import br.pucminas.aws.finalwork.repository.TorneioRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@Service
@AllArgsConstructor
public class TorneioService {

    @Autowired
    private final TorneioRepository repository;

    @Autowired
    private final TimeService timeService;

    @Autowired
    private final PartidaService partidaService;

    public Torneio criar(TorneioDTO dto) {
        Torneio torneio = new Torneio();
        torneio.setNome(dto.getNome());
        return this.repository.save(torneio);
    }

    public Torneio getTorneio(Long id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Torneio not found"));
    }

    public List<Torneio> listar() {
        return this.repository.findAll();
    }

    public Torneio atualizar(Long id, TorneioDTO dto) {
        Torneio torneio = this.getTorneio(id);
        torneio.setNome(dto.getNome());
        return this.repository.save(torneio);
    }

    public void deletar(Long id) {
        Torneio torneio = this.getTorneio(id);
        this.repository.delete(torneio);
    }

    public void adicionarTimeNoTorneio(Long id, @Valid TorneioTimeDTO dto) {
        Torneio torneio = this.getTorneio(id);
        try {
            Time time = this.timeService.getTime(dto.getTimeId());
            if (torneio.getTimes().contains(time)) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Time ja adicionado");
            }
            torneio.getTimes().add(time);
        } catch (ResponseStatusException rsex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Time invalido", rsex);
        }
        this.repository.save(torneio);
    }

    public void removerTimeNoTorneio(Long torneioId, Long timeId) {
        Torneio torneio = this.getTorneio(torneioId);
        Time time = this.timeService.getTime(timeId);
        if (!torneio.getTimes().contains(time)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Time nao esta no torneio");
        }
        torneio.getTimes().remove(time);
        this.repository.save(torneio);
    }

    public Partida adicionarPartidaNoTorneio(Long torneioId, PartidaDTO dto) {
        Torneio torneio = this.getTorneio(torneioId);
        return this.partidaService.adicionarPartida(torneio, dto);
    }

    public List<Partida> listarPartidasPorTorneioId(Long torneioId) {
        Torneio torneio = this.getTorneio(torneioId);
        return this.partidaService.listarPorTorneioId(torneio.getId());
    }

    public Partida atualizarPartidaNoTorneio(Long torneioId, Long partidaId, PartidaDTO dto) {
        Torneio torneio = this.getTorneio(torneioId);
        return this.partidaService.atualizarPartida(torneio, partidaId, dto);
    }

    public void deletarPartidaDoTorneio(Long torneioId, Long partidaId) {
        Torneio torneio = this.getTorneio(torneioId);
        this.partidaService.deletarPartida(torneio, partidaId);
    }
}
