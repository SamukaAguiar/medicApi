package med.voll.medicApi.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.medicApi.paciente.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

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
    public void setPacientes(@RequestBody @Valid PacienteDtoCreate paciente){
        repository.save(new Paciente(paciente));
    }

    @GetMapping
    public Page<PacienteDtoGetAll> getPacientes(@PageableDefault(size = 10, sort = {"nome"}) Pageable page){
        System.out.println(page);
        return repository.findAllByAtivoTrue(page).map(PacienteDtoGetAll::new);
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid PacienteDtoUpdate pacientePar){
        var paciente = repository.getReferenceById(pacientePar.id());
        paciente.updatePaciente(pacientePar);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void excluir(@PathVariable Long id){
        var paciente = repository.getReferenceById(id);
        paciente.deletePaciente();
    }

}
