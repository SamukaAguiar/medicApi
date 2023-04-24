package med.voll.medicApi.domain.paciente;

import med.voll.medicApi.domain.endereco.Endereco;

public record PacienteDtoGetAll(Long id, String nome, String telefone, String email, String cpf, Endereco endereco) {


    public PacienteDtoGetAll(Paciente paciente){
        this(paciente.getId(), paciente.getNome(), paciente.getTelefone(), paciente.getEmail(), paciente.getCpf(), paciente.getEndereco());
    }

}
