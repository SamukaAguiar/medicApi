package med.voll.medicApi.domain.consulta.validacoes.agendamento;

import med.voll.medicApi.domain.consulta.ConsultaDtoCreate;
import med.voll.medicApi.domain.consulta.ConsultaRepository;
import med.voll.medicApi.domain.exceptions.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PacienteConsultaMarcadaNoDia implements AgendamentoValidate{

    @Autowired
    private ConsultaRepository repository;

    public void validar(ConsultaDtoCreate dados){
        var inicioExpediente = dados.data().withHour(7);
        var fimExpediente = dados.data().withHour(18);
        var pacientePossuiConsultaNoDia = repository.existsByPacienteIdAndDataBetween(dados.idPaciente(), inicioExpediente, fimExpediente);
        if (pacientePossuiConsultaNoDia){
            throw new ValidacaoException("Paciente já possui uma consulta agendada neste dia! Isso não é permitido!");
        }
    }

}
