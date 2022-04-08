package br.pucminas.aws.finalwork.resource;

import br.pucminas.aws.finalwork.domain.Transferencia;
import br.pucminas.aws.finalwork.dto.TransferenciaDTO;
import br.pucminas.aws.finalwork.service.TransferenciaService;
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
@RequestMapping("/transferencias")
@AllArgsConstructor
@Tag(name = "Transferencias")
public class TransferenciaResource {

    @Autowired
    private final TransferenciaService service;

    @GetMapping("/{id}")
    @Operation(summary = "Buscar transferencia por ID", description = "Permite recuperar os dados de uma transferencia pelo ID")
    public ResponseEntity<Transferencia> getTransferencia(@Valid @NotNull @PathVariable("id") Long id) {
        return ResponseEntity.ok(this.service.getTransferencia(id));
    }

    @GetMapping
    @Operation(summary = "Listar transferencias", description = "Permite listar as transferencias realizadas")
    public ResponseEntity<List<Transferencia>> listar() {
        return ResponseEntity.ok(this.service.listar());
    }

    @PostMapping
    @Operation(summary = "Realizar transferencia", description = "Permite realizar a transferencia de um jogador para outro time")
    public ResponseEntity<Transferencia> criar(@Valid @RequestBody TransferenciaDTO dto) {
        return new ResponseEntity<>(this.service.criar(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar transferencia", description = "Permite atualizar os dados de uma transferencia existente")
    public ResponseEntity<Transferencia> atualizar(@Valid @NotNull @PathVariable("id") Long id,
                                                   @Valid @RequestBody TransferenciaDTO dto) {
        return ResponseEntity.ok(this.service.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar transferencia", description = "Permite deletar uma transferencia existente")
    public ResponseEntity<Void> deletar(@Valid @NotNull @PathVariable("id") Long id) {
        this.service.deletar(id);
        return ResponseEntity.ok().build();
    }

}
