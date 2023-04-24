package med.voll.medicApi.domain.medico;

import med.voll.medicApi.domain.endereco.Endereco;

public record MedicoDtoGetAll(Long id, String nome, String email, String crm, String telefone, Especialidade especialidade, Endereco endereco) {

    public MedicoDtoGetAll(Medico medico){
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getTelefone(), medico.getEspecialidade(), medico.getEndereco());
    }
}
