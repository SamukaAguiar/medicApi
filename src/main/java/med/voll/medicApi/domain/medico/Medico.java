package med.voll.medicApi.domain.medico;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.medicApi.domain.endereco.Endereco;

@Table(name = "medicos")
@Entity(name = "Medico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String crm;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    @Embedded
    private Endereco endereco;

    private boolean ativo;

    public Medico(MedicoDtoCreate medico) {
        this.nome = medico.nome();
        this.email = medico.email();
        this.telefone = medico.telefone();
        this.crm = medico.crm();
        this.especialidade = medico.especialidade();
        this.endereco = new Endereco(medico.endereco());
        this.ativo = true;
    }

    public void updateMedico(MedicoDtoUpdate medicoPar) {
        if (medicoPar.nome() != null)
            this.nome = medicoPar.nome();

        if (medicoPar.telefone() != null)
            this.telefone = medicoPar.telefone();

        if (medicoPar.endereco() != null)
            this.endereco.updateEndereco(medicoPar.endereco());
    }

    public void deleteMedico(){
        this.ativo = false;
    }
}
