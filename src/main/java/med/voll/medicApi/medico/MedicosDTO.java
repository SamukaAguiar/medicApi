package med.voll.medicApi.medico;

public record MedicosDTO(
        String nome,
        String email,
        String crm,
        Especialidade especialidade
    ) {

    public MedicosDTO(Medico medico){
        this(medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }
}
