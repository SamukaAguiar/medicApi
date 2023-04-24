package med.voll.medicApi.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.medicApi.domain.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid MedicoDtoCreate medicoPar, UriComponentsBuilder uriBuilder){
        var medico = new Medico(medicoPar);
        repository.save(medico);

        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();

        return ResponseEntity.created(uri).body(new MedicoDtoGetAll(medico));
    }

    @GetMapping
    public ResponseEntity<Page<MedicoDtoGetting>> listarMedicos(@PageableDefault(size = 10, sort = {"nome", "crm"}) Pageable pageable){
        var page = repository.findAllByAtivoTrue(pageable).map(MedicoDtoGetting::new);

        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity medico(@PathVariable Long id){
        var medico = repository.getReferenceById(id);
        return ResponseEntity.ok(new MedicoDtoGetAll(medico));
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid MedicoDtoUpdate medicoPar){
        var medico = repository.getReferenceById(medicoPar.id());
        medico.updateMedico(medicoPar);

        return ResponseEntity.ok(new MedicoDtoGetAll(medico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id){
        var medico = repository.getReferenceById(id);
        medico.deleteMedico();

        return ResponseEntity.noContent().build();
    }
}
