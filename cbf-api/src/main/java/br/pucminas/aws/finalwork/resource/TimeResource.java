package br.pucminas.aws.finalwork.resource;

import br.pucminas.aws.finalwork.domain.Time;
import br.pucminas.aws.finalwork.dto.TimeDTO;
import br.pucminas.aws.finalwork.service.TimeService;
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
@RequestMapping("/times")
@AllArgsConstructor
@Tag(name = "Times")
public class TimeResource {

    @Autowired
    private final TimeService service;

    @GetMapping
    @Operation(summary = "Listar times", description = "Permite listar os times cadastrados")
    public ResponseEntity<List<Time>> listar() {
        return ResponseEntity.ok(this.service.listar());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar time por ID", description = "Permite recuperar os dados de um time pelo ID")
    public ResponseEntity<Time> getTime(@Valid @NotNull @PathVariable("id") Long id) {
        return ResponseEntity.ok(this.service.getTime(id));
    }

    @PostMapping
    @Operation(summary = "Cadastrar time", description = "Permite cadastrar um novo time")
    public ResponseEntity<Time> criar(@Valid @RequestBody TimeDTO dto) {
        return new ResponseEntity<>(this.service.criar(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar time", description = "Permite atualizar os dados de um time existente")
    public ResponseEntity<Time> atualizar(@Valid @NotNull @PathVariable("id") Long id, @Valid @RequestBody TimeDTO dto) {
        return ResponseEntity.ok(this.service.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar time", description = "Permite deletar um time existente")
    public ResponseEntity<Void> deletar(@Valid @NotNull @PathVariable("id") Long id) {
        this.service.deletar(id);
        return ResponseEntity.ok().build();
    }
}
