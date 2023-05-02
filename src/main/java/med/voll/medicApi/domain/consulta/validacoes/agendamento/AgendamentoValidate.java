package med.voll.medicApi.domain.consulta.validacoes.agendamento;

import med.voll.medicApi.domain.consulta.ConsultaDtoCreate;

public interface AgendamentoValidate {

    void validar(ConsultaDtoCreate dados);
}
