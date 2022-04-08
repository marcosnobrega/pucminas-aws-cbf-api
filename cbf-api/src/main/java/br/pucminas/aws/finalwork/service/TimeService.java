package br.pucminas.aws.finalwork.service;

import br.pucminas.aws.finalwork.domain.Time;
import br.pucminas.aws.finalwork.dto.TimeDTO;
import br.pucminas.aws.finalwork.repository.TimeRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class TimeService {

    @Autowired
    private final TimeRepository repository;

    public List<Time> listar() {
        return this.repository.findAll();
    }

    public Time criar(TimeDTO dto) {
        Time time = new Time();
        time.setNome(dto.getNome());
        time.setLocalidade(dto.getLocalidade());
        return this.repository.save(time);
    }

    public Time atualizar(Long id, TimeDTO dto) {
        Time time = this.getTime(id);
        time.setNome(dto.getNome());
        time.setLocalidade(dto.getLocalidade());
        return this.repository.save(time);
    }

    public void deletar(Long id) {
        Time time = this.getTime(id);
        this.repository.delete(time);
    }

    public Time getTime(Long id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Time Not Found"));
    }
}
