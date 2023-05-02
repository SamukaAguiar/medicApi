package med.voll.medicApi.domain.consulta.validacoes.agendamento;

import med.voll.medicApi.domain.consulta.ConsultaDtoCreate;
import med.voll.medicApi.domain.consulta.ConsultaRepository;
import med.voll.medicApi.domain.exceptions.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MedicoOcupadoValidate implements AgendamentoValidate{

    @Autowired
    private ConsultaRepository repository;

    public void validar(ConsultaDtoCreate dados){
        var medicoOcupado = repository.existsByMedicoIdAndData(dados.idMedico(), dados.data());

        System.out.println(medicoOcupado);

        if (medicoOcupado){
            throw new ValidacaoException("Médico já possui outra consulta agendada neste horário!");
        }
    }
}
