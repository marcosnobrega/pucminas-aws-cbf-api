package br.pucminas.aws.finalwork.resource;

import br.pucminas.aws.finalwork.domain.Jogador;
import br.pucminas.aws.finalwork.dto.JogadorDTO;
import br.pucminas.aws.finalwork.service.JogadorService;
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
@RequestMapping("/jogadores")
@AllArgsConstructor
@Tag(name = "Jogadores")
public class JogadorResource {

    @Autowired
    private final JogadorService service;

    @GetMapping
    @Operation(summary = "Listar jogadores", description = "Permite listar os jogadores cadastrados")
    public ResponseEntity<List<Jogador>> listar() {
        return ResponseEntity.ok(this.service.listar());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar jogador por ID", description = "Permite recuperar os dados de um jogador pelo ID")
    public ResponseEntity<Jogador> getJogador(@Valid @NotNull @PathVariable("id") Long id) {
        return ResponseEntity.ok(this.service.getJogador(id));
    }

    @PostMapping
    @Operation(summary = "Cadastrar jogador", description = "Permite cadastrar um novo jogador")
    public ResponseEntity<Jogador> criar(@Valid @RequestBody JogadorDTO dto) {
        return new ResponseEntity<>(this.service.criar(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar jogador", description = "Permite atualizar os dados de um jogador existente")
    public ResponseEntity<Jogador> atualizar(@Valid @NotNull @PathVariable("id") Long id,
                                             @Valid @RequestBody JogadorDTO dto) {
        return ResponseEntity.ok(this.service.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar jogador", description = "Permite deletar um jogador existente")
    public ResponseEntity<Void> deletar(@Valid @NotNull @PathVariable("id") Long id) {
        this.service.deletar(id);
        return ResponseEntity.ok().build();
    }

}
