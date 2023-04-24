package med.voll.medicApi.paciente;

import jakarta.validation.constraints.NotNull;
import med.voll.medicApi.endereco.EnderecoDto;

public record PacienteDtoUpdate(
        @NotNull
        Long id,
        String nome,
        String telefone,
        EnderecoDto endereco) {
}
