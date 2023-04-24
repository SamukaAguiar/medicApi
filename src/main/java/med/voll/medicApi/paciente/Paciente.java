package med.voll.medicApi.paciente;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.medicApi.endereco.Endereco;

@Getter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Paciente")
@Table(name = "pacientes")
public class Paciente {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome ;
    private String email;
    private String cpf;
    private String telefone;

    @Embedded
    private Endereco endereco;

    private boolean ativo;

    public Paciente(PacienteDtoCreate paciente){
        this.nome = paciente.nome();
        this.email = paciente.email();
        this.cpf = paciente.cpf();
        this.telefone = paciente.telefone();
        this.endereco = new Endereco(paciente.endereco());
        this.ativo = true;
    }

    public void updatePaciente(PacienteDtoUpdate paciente) {
        if (paciente.nome() != null)
            this.nome = paciente.nome();

        if (paciente.telefone() != null)
            this.telefone = paciente.telefone();

        if (paciente.endereco() != null)
            this.endereco.updateEndereco(paciente.endereco());
    }

    public void deletePaciente() {
        this.ativo = false;
    }
}
