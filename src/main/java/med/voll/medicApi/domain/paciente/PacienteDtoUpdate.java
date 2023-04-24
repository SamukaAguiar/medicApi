package med.voll.medicApi.domain.paciente;

import jakarta.validation.constraints.NotNull;
import med.voll.medicApi.domain.endereco.EnderecoDto;

public record PacienteDtoUpdate(
        @NotNull
        Long id,
        String nome,
        String telefone,
        EnderecoDto endereco) {
}
