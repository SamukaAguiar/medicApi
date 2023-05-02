package med.voll.medicApi.domain.consulta;

import java.time.LocalDateTime;

public record ConsultaCanceladaDto(Long id, LocalDateTime data, boolean cancelado, String motivoCancelamento) {

    public ConsultaCanceladaDto(Consulta consulta){
        this(consulta.getId(), consulta.getData(), consulta.isCancelado(), consulta.getMotivoCancelamento());
    }
}
