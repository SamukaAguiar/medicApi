package med.voll.medicApi.domain.consulta;

import jakarta.validation.constraints.NotNull;

public record CancelamentoConsultaDto(
        @NotNull
        Long idConsulta,
        String motivoCancelamento){
}
