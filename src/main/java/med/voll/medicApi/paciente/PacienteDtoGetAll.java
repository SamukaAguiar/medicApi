package med.voll.medicApi.paciente;

public record PacienteDtoGetAll(Long id, String nome, String email, String cpf) {

    public PacienteDtoGetAll(Paciente paciente){

        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getCpf());
    }
}
