package br.pucminas.aws.finalwork.resource;

import br.pucminas.aws.finalwork.domain.Evento;
import br.pucminas.aws.finalwork.domain.Partida;
import br.pucminas.aws.finalwork.domain.Torneio;
import br.pucminas.aws.finalwork.dto.PartidaDTO;
import br.pucminas.aws.finalwork.dto.TorneioDTO;
import br.pucminas.aws.finalwork.dto.TorneioTimeDTO;
import br.pucminas.aws.finalwork.service.EventoService;
import br.pucminas.aws.finalwork.service.TorneioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/torneios")
@AllArgsConstructor
@Tag(name = "Torneios")
public class TorneioResource {

    @Autowired
    private final TorneioService service;

    @Autowired
    private final EventoService eventoService;

    @PostMapping
    @Operation(summary = "Cadastrar torneio", description = "Permite cadastrar um novo torneio")
    public ResponseEntity<Torneio> criar(@Valid @RequestBody TorneioDTO dto) {
        return new ResponseEntity<>(this.service.criar(dto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar torneio por ID", description = "Permite recuperar os dados de um torneio")
    public ResponseEntity<Torneio> getTorneio(@Valid @NotNull @PathVariable("id") Long id) {
        return ResponseEntity.ok(this.service.getTorneio(id));
    }

    @GetMapping
    @Operation(summary = "Listar torneios", description = "Permite listar os torneios cadastrados")
    public ResponseEntity<List<Torneio>> listar() {
        return ResponseEntity.ok(this.service.listar());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar torneio", description = "Permite atualizar os dados de um torneio existente")
    public ResponseEntity<Torneio> atualizar(@Valid @NotNull @PathVariable("id") Long id,
                                             @Valid @RequestBody TorneioDTO dto) {
        return ResponseEntity.ok(this.service.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remover torneio", description = "Permite deletar um torneio existente")
    public ResponseEntity<Void> deletar(@Valid @NotNull @PathVariable("id") Long id) {
        this.service.deletar(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/times")
    @Operation(summary = "Adicionar time no torneio", description = "Permite adicionar um dos times cadastrados em um torneio")
    public ResponseEntity<Void> adicionarTimeNoTorneio(@Valid @NotNull @PathVariable("id") Long id,
                                                       @Valid @RequestBody TorneioTimeDTO dto) {
        this.service.adicionarTimeNoTorneio(id, dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{torneioId}/times/{timeId}")
    @Operation(summary = "Remover time do torneio", description = "Permite remover um dos times do torneio")
    public ResponseEntity<Void> removerTimeNoTorneio(@Valid @NotNull @PathVariable("torneioId") Long torneioId,
                                                     @Valid @NotNull @PathVariable("timeId") Long timeId) {
        this.service.removerTimeNoTorneio(torneioId, timeId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/partidas")
    @Operation(summary = "Adicionar partida no torneio", description = "Permite adicionar uma partida em um torneio")
    public ResponseEntity<Partida> adicionarPartidaNoTorneio(@Valid @NotNull @PathVariable("id") Long torneioId,
                                                             @Valid @RequestBody PartidaDTO dto) {
        return new ResponseEntity<>(this.service.adicionarPartidaNoTorneio(torneioId, dto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}/partidas")
    @Operation(summary = "Listar partidas do torneio", description = "Permite listar as partidas cadastradas em um torneio")
    public ResponseEntity<List<Partida>> listarPartidasDoTorneio(@Valid @NotNull @PathVariable("id") Long torneioId) {
        return ResponseEntity.ok(this.service.listarPartidasPorTorneioId(torneioId));
    }

    @PutMapping("/{torneioId}/partidas/{partidaId}")
    @Operation(summary = "Atualizar partida no torneio", description = "Permite atualizar uma partida existente em um torneio")
    public ResponseEntity<Partida> atualizarPartidaNoTorneio(@Valid @NotNull @PathVariable("torneioId") Long torneioId,
                                                             @Valid @NotNull @PathVariable("partidaId") Long partidaId,
                                                             @Valid @RequestBody PartidaDTO dto) {
        return ResponseEntity.ok(this.service.atualizarPartidaNoTorneio(torneioId, partidaId, dto));
    }

    @DeleteMapping("/{torneioId}/partidas/{partidaId}")
    @Operation(summary = "Deletar partida do torneio", description = "Permite deletar uma partida existente em um torneio")
    public ResponseEntity<Void> deletarPartidaDoTorneio(@Valid @NotNull @PathVariable("torneioId") Long torneioId,
                                                        @Valid @NotNull @PathVariable("partidaId") Long partidaId) {
        this.service.deletarPartidaDoTorneio(torneioId, partidaId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{torneioId}/partidas/{partidaId}/eventos/inicio")
    @Operation(summary = "Registrar inicio da partida", description = "Permite registrar o evento de inicio de uma partida em um torneio")
    public ResponseEntity<Void> iniciarPartida(@Valid @NotNull @PathVariable("torneioId") Long torneioId,
                                               @Valid @NotNull @PathVariable("partidaId") Long partidaId) {
        this.eventoService.registrarInicio(torneioId, partidaId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{torneioId}/partidas/{partidaId}/eventos/gol")
    @Operation(summary = "Registrar gol na partida", description = "Permite registrar o evento de gol em uma partida em um torneio")
    public ResponseEntity<Void> marcarGol(@Valid @NotNull @PathVariable("torneioId") Long torneioId,
                                          @Valid @NotNull @PathVariable("partidaId") Long partidaId) {
        this.eventoService.registrarGol(torneioId, partidaId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{torneioId}/partidas/{partidaId}/eventos/intervalo")
    @Operation(summary = "Registrar intervalo da partida", description = "Permite registrar o evento de intervalo de uma partida em um torneio")
    public ResponseEntity<Void> intervaloDaPartida(@Valid @NotNull @PathVariable("torneioId") Long torneioId,
                                                   @Valid @NotNull @PathVariable("partidaId") Long partidaId) {
        this.eventoService.registrarIntervalo(torneioId, partidaId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{torneioId}/partidas/{partidaId}/eventos/acrescimo")
    @Operation(summary = "Registrar acrescimo na partida", description = "Permite registrar o evento de acrescimo em uma partida em um torneio")
    public ResponseEntity<Void> acrescimoPartida(@Valid @NotNull @PathVariable("torneioId") Long torneioId,
                                                 @Valid @NotNull @PathVariable("partidaId") Long partidaId) {
        this.eventoService.registrarAcrescimo(torneioId, partidaId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{torneioId}/partidas/{partidaId}/eventos/substituicao")
    @Operation(summary = "Registrar substituicao na partida", description = "Permite registrar o evento de substituicao em uma partida em um torneio")
    public ResponseEntity<Void> substituicaoPartida(@Valid @NotNull @PathVariable("torneioId") Long torneioId,
                                                    @Valid @NotNull @PathVariable("partidaId") Long partidaId) {
        this.eventoService.registrarSubstituicao(torneioId, partidaId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{torneioId}/partidas/{partidaId}/eventos/advertencia")
    @Operation(summary = "Registrar advertencia na partida", description = "Permite registrar o evento de advertencia em uma partida em um torneio")
    public ResponseEntity<Void> advertenciaPartida(@Valid @NotNull @PathVariable("torneioId") Long torneioId,
                                                   @Valid @NotNull @PathVariable("partidaId") Long partidaId) {
        this.eventoService.registrarAdvertencia(torneioId, partidaId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{torneioId}/partidas/{partidaId}/eventos/fim")
    @Operation(summary = "Registrar fim da partida", description = "Permite registrar o evento de fim de uma partida em um torneio")
    public ResponseEntity<Void> finalizarPartida(@Valid @NotNull @PathVariable("torneioId") Long torneioId,
                                                 @Valid @NotNull @PathVariable("partidaId") Long partidaId) {
        this.eventoService.registrarFim(torneioId, partidaId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{torneioId}/partidas/{partidaId}/eventos")
    @Operation(summary = "Listar eventos da partida", description = "Permite listar os eventos de uma partida em um torneio")
    public ResponseEntity<List<Evento>> listarEventos(@Valid @NotNull @PathVariable("torneioId") Long torneioId,
                                                      @Valid @NotNull @PathVariable("partidaId") Long partidaId) {
        return ResponseEntity.ok(this.eventoService.listarEventosDaPartida(torneioId, partidaId));
    }

}
