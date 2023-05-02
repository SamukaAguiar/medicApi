package med.voll.medicApi.domain.consulta.validacoes.agendamento;

import med.voll.medicApi.domain.consulta.ConsultaDtoCreate;
import med.voll.medicApi.domain.exceptions.ValidacaoException;
import med.voll.medicApi.domain.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MedicoAtivoValidate implements AgendamentoValidate{

    @Autowired
    private MedicoRepository medicoRepository;

    public void validar(ConsultaDtoCreate dados){

        if (dados.idMedico() == null){
            return;
        }

        var medicoEstaAtivo = medicoRepository.findAtivoById(dados.idMedico());
        if (!medicoEstaAtivo) {
            throw new ValidacaoException("Consultas não podem ser agendadas com médicos excluidos");
        }
    }
}
