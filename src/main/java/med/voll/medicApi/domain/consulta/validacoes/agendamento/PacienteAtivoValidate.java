package med.voll.medicApi.domain.consulta.validacoes.agendamento;

import med.voll.medicApi.domain.consulta.ConsultaDtoCreate;
import med.voll.medicApi.domain.exceptions.ValidacaoException;
import med.voll.medicApi.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PacienteAtivoValidate implements AgendamentoValidate{

    @Autowired
    private PacienteRepository repository;

    public void validar(ConsultaDtoCreate dados){
        var pacienteAtivo = repository.findAtivoById(dados.idPaciente());
        if(!pacienteAtivo){
            throw new ValidacaoException("Paciente informado foi excluido!");
        }
    }

}
