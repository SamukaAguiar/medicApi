package med.voll.medicApi.domain.consulta.validacoes.agendamento;

import med.voll.medicApi.domain.consulta.ConsultaDtoCreate;
import med.voll.medicApi.domain.exceptions.ValidacaoException;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class HorarioFuncionamentoValidate implements AgendamentoValidate{

    public void validar(ConsultaDtoCreate dados){
        var dataConsulta = dados.data();
        var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var abertura = dataConsulta.getHour() < 7;
        var fechamento = dataConsulta.getHour() < 18;

        if (domingo || (abertura && fechamento)){
            throw new ValidacaoException("Consulta fora do horário de funcionamento da clinica");
        }
    }
}
