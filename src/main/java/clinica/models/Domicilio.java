package clinica.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@NotNull
@Table(name="domicilio")
public class Domicilio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "calle")
    private String calle;
    @Column(name = "localidad")
    private String localidad;
    @Column(name = "ciudad")
    private String ciudad;


    @OneToOne( mappedBy = "domicilio",cascade = CascadeType.ALL)
    private Paciente paciente;

    public Domicilio(Long id, String calle, String localidad, String ciudad) {
        this.id = id;
        this.calle = calle;
        this.localidad = localidad;
        this.ciudad = ciudad;
    }
}


