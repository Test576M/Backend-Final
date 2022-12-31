package clinica.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "paciente")
public class Paciente implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column (name = "nombre")
    private String nombre;
    @Column(name = "apellido")
    private String apellido;
    @Column(unique = true)
    private String dni;
    @Column
    private LocalDateTime fechaAlta;


    @OneToOne (cascade = {CascadeType.ALL})
    @JsonIgnore

    private Domicilio domicilio;
    public Paciente(String nombre, String apellido, String dni, LocalDateTime fecha) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.fechaAlta = fecha;
    }

    @OneToMany(
            mappedBy = "paciente",
            cascade = CascadeType.ALL,
            orphanRemoval = true)


    private List<Turno> turnosPaciente = new ArrayList<>();

    public Paciente(long id, String nombre, String apellido, String dni, LocalDateTime fechaAlta, Domicilio domicilio) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.fechaAlta = fechaAlta;
        this.domicilio = domicilio;
    }

    public void addTurno(Turno turno){
        turnosPaciente.add(turno);
        turno.setPaciente(this);
    }
    public void removeTurno(Turno turno) {
        turnosPaciente.add(turno);
        turno.setPaciente(this);
    }

}
