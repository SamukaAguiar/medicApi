package med.voll.medicApi.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.medicApi.domain.paciente.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("pacientes")
public class PacienteController {

    @Autowired
    private final PacienteRepository repository;

    public PacienteController(PacienteRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid PacienteDtoCreate pacientePar, UriComponentsBuilder uriBuilder){
        var paciente = new Paciente(pacientePar);
        repository.save(paciente);

        var uri = uriBuilder.path("/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();

        return ResponseEntity.created(uri).body(new PacienteDtoGetAll(paciente));
    }

    @GetMapping
    public ResponseEntity<Page<PacienteDtoGetting>> listarPacientes(@PageableDefault(size = 10, sort = {"nome"}) Pageable pagePar){
        var page = repository.findAllByAtivoTrue(pagePar).map(PacienteDtoGetting::new);

        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity paciente(@PathVariable Long id){
        var paciente = repository.getReferenceById(id);
        return ResponseEntity.ok(new PacienteDtoGetAll(paciente));
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid PacienteDtoUpdate pacientePar){
        var paciente = repository.getReferenceById(pacientePar.id());
        paciente.updatePaciente(pacientePar);

        return ResponseEntity.ok(new PacienteDtoGetAll(paciente));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id){
        var paciente = repository.getReferenceById(id);
        paciente.deletePaciente();

        return ResponseEntity.noContent().build();
    }

}
