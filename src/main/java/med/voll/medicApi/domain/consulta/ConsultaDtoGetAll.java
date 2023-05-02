package med.voll.medicApi.domain.consulta;

import med.voll.medicApi.domain.medico.Medico;
import med.voll.medicApi.domain.paciente.Paciente;

import java.time.LocalDateTime;

public record ConsultaDtoGetAll(Long id, Long idMedico, Long idPaciente, LocalDateTime data) {

    public ConsultaDtoGetAll(Consulta consulta){
        this(consulta.getId(), consulta.getMedico().getId(), consulta.getPaciente().getId(), consulta.getData());
    }
}
