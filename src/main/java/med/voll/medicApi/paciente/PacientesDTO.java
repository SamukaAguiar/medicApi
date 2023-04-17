package med.voll.medicApi.paciente;

public record PacientesDTO(String nome, String email, String cpf) {

    public PacientesDTO(Paciente paciente){
        this(paciente.getNome(), paciente.getEmail(), paciente.getCpf());
    }
}
