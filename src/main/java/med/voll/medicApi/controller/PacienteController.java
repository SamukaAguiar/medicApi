package med.voll.medicApi.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.medicApi.medico.Medico;
import med.voll.medicApi.medico.Medicos;
import med.voll.medicApi.medico.MedicosDTO;
import med.voll.medicApi.paciente.Paciente;
import med.voll.medicApi.paciente.PacienteRepository;
import med.voll.medicApi.paciente.Pacientes;
import med.voll.medicApi.paciente.PacientesDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository repository;

    @PostMapping
    @Transactional
    public void setPacientes(@RequestBody @Valid Pacientes paciente){
        System.out.println(paciente);
        repository.save(new Paciente(paciente));
    }

    @GetMapping
    public Page<PacientesDTO> getPacientes(@PageableDefault(size = 10, sort = {"nome"}) Pageable page){
        System.out.println(page);
        return repository.findAll(page).map(PacientesDTO::new);
    }

}
