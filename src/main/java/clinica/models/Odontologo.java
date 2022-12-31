package clinica.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "odontologo")

public class Odontologo{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column (name="nombre")
    private String nombre;
    @Column(name="apellido")
    private String apellido;
    @Column(name="matricula")
    private String matricula;

    @JsonIgnore
    @OneToMany( mappedBy = "odontologo",fetch = FetchType.LAZY, cascade = CascadeType.ALL)

    private List<Turno> turnosOdontologo = new ArrayList<>();


    public Odontologo(String nombre, String apellido, String matricula) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.matricula = matricula;
    }

    public void addTurno(Turno turno){
        turnosOdontologo.add(turno);
        turno.setOdontologo(this);
    }

    public void removeTurno(Turno turno){
        turnosOdontologo.add(turno);
        turno.setOdontologo(this);
    }
}
