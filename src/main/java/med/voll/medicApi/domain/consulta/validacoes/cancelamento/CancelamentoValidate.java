package med.voll.medicApi.domain.consulta.validacoes.cancelamento;

import med.voll.medicApi.domain.consulta.CancelamentoConsultaDto;

public interface CancelamentoValidate {

    void validar(CancelamentoConsultaDto dados);
}
