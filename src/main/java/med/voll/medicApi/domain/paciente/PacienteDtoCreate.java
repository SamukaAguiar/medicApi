package med.voll.medicApi.domain.paciente;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.medicApi.domain.endereco.EnderecoDto;

public record PacienteDtoCreate(
        @NotBlank
        String nome,
        @NotBlank
        @Email
        String email,
        @NotBlank
        @Pattern(regexp = "(\\d{3}\\x2E\\d{3}\\x2E\\d{3}\\x2D\\d{2}$)")
        String cpf,
        @NotBlank
        @Pattern(regexp = "\\(?\\d{2,}\\)?[ -]?\\d{4,}[\\-\\s]?\\d{4}")
        String telefone,
        @NotNull
        @Valid
        EnderecoDto endereco ) {
}
