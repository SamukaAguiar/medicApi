package med.voll.medicApi.domain.medico;

import jakarta.validation.constraints.NotNull;
import med.voll.medicApi.domain.endereco.EnderecoDto;

public record MedicoDtoUpdate(
        @NotNull
        Long id,
        String nome,
        String telefone,
        EnderecoDto endereco) {
}
