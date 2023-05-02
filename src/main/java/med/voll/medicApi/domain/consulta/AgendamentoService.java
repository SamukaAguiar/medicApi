package med.voll.medicApi.domain.consulta;

import med.voll.medicApi.domain.consulta.validacoes.agendamento.AgendamentoValidate;
import med.voll.medicApi.domain.consulta.validacoes.cancelamento.CancelamentoValidate;
import med.voll.medicApi.domain.exceptions.ValidacaoException;
import med.voll.medicApi.domain.medico.Medico;
import med.voll.medicApi.domain.medico.MedicoRepository;
import med.voll.medicApi.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendamentoService {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private List<AgendamentoValidate> validadorAgendamento;

    @Autowired
    private List<CancelamentoValidate> validadorCancelamento;

    public ConsultaDtoGetAll agendar(ConsultaDtoCreate consultaPar){
        if (!pacienteRepository.existsById(consultaPar.idPaciente())){
            throw new ValidacaoException("Id do Paciente informado não esta cadastrado!");
        }

        if (consultaPar.idMedico() != null && !medicoRepository.existsById(consultaPar.idMedico())){
            throw new ValidacaoException("Id do Médico informado não esta cadastrado!");
        }

        validadorAgendamento.forEach(v -> v.validar(consultaPar));

        var paciente = pacienteRepository.getReferenceById(consultaPar.idPaciente());
        var medico = escolheMedico(consultaPar);

        if (medico == null) {
            throw new ValidacaoException("Não existe médico disponivel nesta data e nesta especialidade!");
        }

        var consulta = new Consulta(null, medico, paciente, consultaPar.data(), false, null);

        consultaRepository.save(consulta);

        return new ConsultaDtoGetAll(consulta);
    }

    private Medico escolheMedico(ConsultaDtoCreate dados) {
        if (dados.idMedico() != null){
            return medicoRepository.getReferenceById(dados.idMedico());
        }

        if (dados.especialidade() == null){
            throw new ValidacaoException("Especialidade é obrigatória quando o médico não é informado!");
        }

        return medicoRepository.escolheMedicoAleatorioNaData(dados.especialidade(), dados.data());
    }

    public ConsultaCanceladaDto cancelarConsulta(CancelamentoConsultaDto dados) {
        if (!consultaRepository.existsById(dados.idConsulta())){
            throw new ValidacaoException("Id da consulta não existe no cadastro!");
        }

        validadorCancelamento.forEach(v -> v.validar(dados));

        var consulta = consultaRepository.getReferenceById(dados.idConsulta());

        consulta.cancelamento(dados.motivoCancelamento());

        return new ConsultaCanceladaDto(consulta);
    }
}
