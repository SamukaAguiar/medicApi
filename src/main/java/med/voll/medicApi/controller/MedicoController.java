package med.voll.medicApi.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.medicApi.medico.Medico;
import med.voll.medicApi.medico.MedicoRepository;
import med.voll.medicApi.medico.Medicos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public void insert(@RequestBody @Valid Medicos medico){
        repository.save(new Medico(medico));
    }

}
