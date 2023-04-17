package med.voll.medicApi.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.medicApi.medico.Medico;
import med.voll.medicApi.medico.MedicoRepository;
import med.voll.medicApi.medico.Medicos;
import med.voll.medicApi.medico.MedicosDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public void setMedicos(@RequestBody @Valid Medicos medico){
        repository.save(new Medico(medico));
    }

    @GetMapping
    public Page<MedicosDTO> getMedicos(@PageableDefault(size = 10, sort = {"nome", "crm"}) Pageable page){
        return repository.findAll(page).map(MedicosDTO::new);
    }
}
