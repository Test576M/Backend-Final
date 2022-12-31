package clinica.models;

import clinica.playload.request.TurnoRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import java.time.LocalDateTime;
import javax.persistence.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "turno")

public class Turno {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JsonIgnore
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(
            name = "odontologo_id", referencedColumnName = "id",nullable = false
            )
    private Paciente paciente;


    @JsonIgnore
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(
            name = "paciente_id", referencedColumnName = "id", nullable = false)

    private Odontologo odontologo;

    private LocalDateTime fecha;


    public Turno( Odontologo odontologo, Paciente paciente,LocalDateTime fecha, Long id) {
        this.id = id;
        this.odontologo = odontologo;
        this.paciente = paciente;
        this.fecha = fecha;

    }

    public static Turno from(TurnoRequest turnoRequest){

        Turno turno = new Turno();

        Odontologo newOdontologo=new Odontologo();
        turno.setId(0);
        newOdontologo.setId(turnoRequest.getOdontologo_id());
        Paciente newPaciente=new Paciente();
        newPaciente.setId(turnoRequest.getPaciente_id());
        turno.setOdontologo(newOdontologo);
        turno.setPaciente(newPaciente);
        turno.setFecha(turnoRequest.getFecha());


        return turno;

    }

    public String message(Turno turno){return   "DR."+turno.getOdontologo().getApellido()+" atiende al Sr./a "+
            turno.getPaciente().getApellido()+" el día "+ turno.getFecha();}
    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

