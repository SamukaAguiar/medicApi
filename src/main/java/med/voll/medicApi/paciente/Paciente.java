package med.voll.medicApi.paciente;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.medicApi.endereco.Endereco;
import org.springframework.boot.autoconfigure.web.WebProperties;

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

    public Paciente(Pacientes paciente){
        this.nome = paciente.nome();
        this.email = paciente.email();;
        this.cpf = paciente.cpf();
        this.telefone = paciente.telefone();
        this.endereco = new Endereco(paciente.endereco());
    }
}
