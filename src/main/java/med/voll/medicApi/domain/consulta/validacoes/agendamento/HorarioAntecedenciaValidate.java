package med.voll.medicApi.domain.consulta.validacoes.agendamento;

import med.voll.medicApi.domain.consulta.ConsultaDtoCreate;
import med.voll.medicApi.domain.exceptions.ValidacaoException;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class HorarioAntecedenciaValidate implements AgendamentoValidate{

    public void validar(ConsultaDtoCreate dados){
        var dataConsulta = dados.data();
        var agora = LocalDateTime.now();
        var difMinutos = Duration.between(agora, dataConsulta).toMinutes();

        if (difMinutos < 30 ){
            throw new ValidacaoException("Consulta deve ser agendada com antecedencia minima de 30 min!");
        }
    }

}
