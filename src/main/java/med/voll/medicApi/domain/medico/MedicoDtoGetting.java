package med.voll.medicApi.domain.medico;

public record MedicoDtoGetting(
        Long id,
        String nome,
        String email,
        String crm,
        Especialidade especialidade
    ) {

    public MedicoDtoGetting(Medico medico){
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }
}
