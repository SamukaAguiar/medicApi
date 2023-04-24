package med.voll.medicApi.medico;

public record MedicoDtoGetAll(
        Long id,
        String nome,
        String email,
        String crm,
        Especialidade especialidade
    ) {

    public MedicoDtoGetAll(Medico medico){
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }
}
