package med.voll.medicApi.domain.consulta.validacoes.cancelamento;

import med.voll.medicApi.domain.consulta.CancelamentoConsultaDto;
import med.voll.medicApi.domain.consulta.ConsultaDtoCreate;
import med.voll.medicApi.domain.consulta.ConsultaRepository;
import med.voll.medicApi.domain.exceptions.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class HorarioAntecedenciaParaCancelamento implements CancelamentoValidate{

    @Autowired
    private ConsultaRepository repository;

    public void validar(CancelamentoConsultaDto dados){
        var consulta = repository.getReferenceById(dados.idConsulta());
        var agora = LocalDateTime.now();
        var difMinutos = Duration.between(agora, consulta.getData()).toHours();

        if (difMinutos < 24 ){
            throw new ValidacaoException("As consulta só podem ser canceladas com no minimo 24 horas de antecedencia!");
        }
    }

}
