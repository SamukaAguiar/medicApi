package med.voll.medicApi.medico;

import med.voll.medicApi.endereco.Enderecos;

public record Medicos(String nome, String email, String crm, Especialidade especialidade, Enderecos endereco) {

}
