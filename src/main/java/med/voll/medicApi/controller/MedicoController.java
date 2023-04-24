package med.voll.medicApi.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.medicApi.medico.*;
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
    public void setMedicos(@RequestBody @Valid MedicoDtoCreate medico){
        repository.save(new Medico(medico));
    }

    @GetMapping
    public Page<MedicoDtoGetAll> getMedicos(@PageableDefault(size = 10, sort = {"nome", "crm"}) Pageable page){
    return repository.findAllByAtivoTrue(page).map(MedicoDtoGetAll::new);
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid MedicoDtoUpdate medicoPar){
        var medico = repository.getReferenceById(medicoPar.id());
        medico.updateMedico(medicoPar);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void excluir(@PathVariable Long id){
        var medico = repository.getReferenceById(id);
        medico.deleteMedico();
    }
}
