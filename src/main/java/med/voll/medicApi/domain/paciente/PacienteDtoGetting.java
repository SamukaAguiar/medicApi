package med.voll.medicApi.domain.paciente;

public record PacienteDtoGetting(Long id, String nome, String email, String cpf) {

    public PacienteDtoGetting(Paciente paciente){

        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getCpf());
    }
}
