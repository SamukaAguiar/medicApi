package med.voll.medicApi.medico;

import jakarta.validation.constraints.NotNull;
import med.voll.medicApi.endereco.EnderecoDto;

public record MedicoDtoUpdate(
        @NotNull
        Long id,
        String nome,
        String telefone,
        EnderecoDto endereco) {
}
