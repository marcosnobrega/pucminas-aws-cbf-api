package br.pucminas.aws.finalwork.service;

import br.pucminas.aws.finalwork.domain.Jogador;
import br.pucminas.aws.finalwork.domain.Transferencia;
import br.pucminas.aws.finalwork.dto.TransferenciaDTO;
import br.pucminas.aws.finalwork.repository.TransferenciaRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class TransferenciaService {

    @Autowired
    private final TransferenciaRepository repository;

    @Autowired
    private final TimeService timeService;

    @Autowired
    private final JogadorService jogadorService;

    public List<Transferencia> listar() {
        return this.repository.findAll();
    }

    public Transferencia getTransferencia(Long id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Transferencia Not Found"));
    }

    public Transferencia criar(TransferenciaDTO dto) {
        Transferencia transferencia = new Transferencia();
        transferencia.setData(dto.getData());
        transferencia.setValor(dto.getValor());
        try {
            Jogador jogador = this.jogadorService.getJogador(dto.getIdJogador());
            transferencia.setTimeDeOrigem(jogador.getTime());
            transferencia.setTimeDeDestino(this.timeService.getTime(dto.getIdTimeDeDestino()));
            transferencia.setJogador(jogador);
        } catch (ResponseStatusException rsex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Dados invalidos", rsex);
        }
        this.jogadorService.transferirJogador(transferencia.getJogador().getId(), transferencia.getTimeDeDestino());
        return this.repository.save(transferencia);
    }

    public Transferencia atualizar(Long id, TransferenciaDTO dto) {
        Transferencia transferencia = this.getTransferencia(id);
        transferencia.setData(dto.getData());
        transferencia.setValor(dto.getValor());
        try {
            transferencia.setTimeDeDestino(this.timeService.getTime(dto.getIdTimeDeDestino()));
            transferencia.setJogador(this.jogadorService.getJogador(dto.getIdJogador()));
        } catch (ResponseStatusException rsex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Dados invalidos", rsex);
        }
        this.jogadorService.transferirJogador(transferencia.getJogador().getId(), transferencia.getTimeDeDestino());
        return this.repository.save(transferencia);
    }

    public void deletar(Long id) {
        Transferencia transferencia = this.getTransferencia(id);
        this.repository.delete(transferencia);
    }
}
