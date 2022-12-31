package clinica.playload.request;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PacienteRequest  {


    private String nombre;
    private String apellido;
    private String domicilio;
    private String dni;
    private String calle;
    private String localidad;
    private String ciudad;
    private LocalDateTime fecha;


}

