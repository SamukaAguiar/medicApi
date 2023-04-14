package med.voll.medicApi.endereco;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record Enderecos(
        @NotBlank
        String logradouro,
        @NotBlank
        String bairro,
        @NotBlank
        @Pattern(regexp = "\\d{8}")
        String cep,
        @NotBlank
        String cidade,
        @NotBlank
        String uf,
        String complemento,
        String numero) {
}
