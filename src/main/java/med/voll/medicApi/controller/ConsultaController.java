package med.voll.medicApi.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.medicApi.domain.consulta.AgendamentoService;
import med.voll.medicApi.domain.consulta.CancelamentoConsultaDto;
import med.voll.medicApi.domain.consulta.ConsultaDtoCreate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UriComponentsBuilder;

@Controller
@SecurityRequirement(name = "bearer-key")
public class ConsultaController {
    @Autowired
    private AgendamentoService agendamento;

    @PostMapping
    @Transactional
    @RequestMapping("/consultas")
    public ResponseEntity agendar(@RequestBody @Valid ConsultaDtoCreate consulta, UriComponentsBuilder uriBuilder){
        System.out.println("passou aqui");
        var agendamentoDto = agendamento.agendar(consulta);

        var uri = uriBuilder.path("/consultas/{id}").buildAndExpand(agendamentoDto.id()).toUri();

        return ResponseEntity.created(uri).body(agendamentoDto);
    }

    @PostMapping
    @RequestMapping(value = "/cancelamento")
    @Transactional
    public ResponseEntity cancelar(@RequestBody @Valid CancelamentoConsultaDto cancel){

        System.out.println("passou aqui 2");
        var cancelamentoDto = agendamento.cancelarConsulta(cancel);

        return ResponseEntity.ok(cancelamentoDto);
    }
}
