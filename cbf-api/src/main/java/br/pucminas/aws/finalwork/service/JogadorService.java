package br.pucminas.aws.finalwork.service;

import br.pucminas.aws.finalwork.domain.Jogador;
import br.pucminas.aws.finalwork.domain.Time;
import br.pucminas.aws.finalwork.dto.JogadorDTO;
import br.pucminas.aws.finalwork.repository.JogadorRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class JogadorService {

    @Autowired
    private final JogadorRepository repository;

    @Autowired
    private final TimeService timeService;

    public Jogador getJogador(Long id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Jogador Not Found"));
    }

    public List<Jogador> listar() {
        return this.repository.findAll();
    }

    public Jogador criar(JogadorDTO dto) {
        Jogador jogador = new Jogador();
        jogador.setNome(dto.getNome());
        jogador.setPais(dto.getPais());
        jogador.setDataDeNascimento(dto.getDataDeNascimento());
        try {
            jogador.setTime(this.timeService.getTime(dto.getIdTime()));
        } catch (ResponseStatusException rsex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Time invalido", rsex);
        }
        return this.repository.save(jogador);
    }

    public Jogador atualizar(Long id, JogadorDTO dto) {
        Jogador jogador = this.getJogador(id);
        jogador.setNome(dto.getNome());
        jogador.setPais(dto.getPais());
        jogador.setDataDeNascimento(dto.getDataDeNascimento());
        try {
            jogador.setTime(this.timeService.getTime(dto.getIdTime()));
        } catch (ResponseStatusException rsex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Time invalido", rsex);
        }
        return this.repository.save(jogador);
    }

    public void deletar(Long id) {
        Jogador jogador = this.getJogador(id);
        this.repository.delete(jogador);
    }

    public void transferirJogador(Long id, Time timeDeDestino) {
        Jogador jogador = this.getJogador(id);
        jogador.setTime(timeDeDestino);
        this.repository.save(jogador);
    }
}
