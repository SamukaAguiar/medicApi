package med.voll.medicApi.domain.consulta;

import jakarta.persistence.*;
import lombok.*;
import med.voll.medicApi.domain.medico.Medico;
import med.voll.medicApi.domain.paciente.Paciente;

import java.time.LocalDateTime;

@Table(name = "consultas")
@Entity(name = "Consulta")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medico_id")
    private Medico medico;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    //@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime data;

    private boolean cancelado = false;

    @Column(name = "motivo_cancelamento")
    private String motivoCancelamento;

    public void cancelamento(String motivoCancelamento) {
        this.cancelado = true;
        this.motivoCancelamento = motivoCancelamento;
    }
}
