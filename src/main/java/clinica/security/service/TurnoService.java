package clinica.security.service;



import clinica.models.Domicilio;
import clinica.models.Paciente;
import clinica.models.Turno;
import clinica.playload.request.PacienteRequest;
import clinica.repositories.PacienteRepository;
import clinica.repositories.TurnoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TurnoService {

    private final TurnoService repository;


    public TurnoService(TurnoRepository turnoRepository) {
        this.repository = (TurnoService) turnoRepository;
    }



    public Turno agregar(Turno turno) {
        turno.getOdontologo().addTurno(turno);
        turno.getPaciente().addTurno(turno);
        return repository.agregar(turno);
    }


    public List<Turno> Listar() {
        return repository.Listar();
    }


    public Turno obtenerPorId(long id) {
        return repository.obtenerPorId(id);
    }


    public Turno modificar (Turno turno, long id) {
        Turno existingTurno = repository.obtenerPorId(id);
        existingTurno.setOdontologo(turno.getOdontologo());
        existingTurno.setPaciente(turno.getPaciente());
        existingTurno.setFecha(turno.getFecha());
        repository.agregar(existingTurno);
        return existingTurno;
    }


    public Turno eliminarTurno(long id) {
        Turno existingTurno = repository.obtenerPorId(id);
        repository.eliminarTurno(id);
        return existingTurno;
    }

}







