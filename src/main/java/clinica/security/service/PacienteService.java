package clinica.security.service;


import clinica.models.Domicilio;
import clinica.models.Paciente;
import clinica.playload.request.PacienteRequest;
import clinica.repositories.PacienteRepository;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
public class PacienteService {

    private final PacienteRepository repository;

    public PacienteService(PacienteRepository pacienteRepository){
        this.repository = pacienteRepository;
    }


    public Paciente agregar (Paciente paciente){
        return repository.save(paciente);
    }


    public List<Paciente> Listar(){return repository.findAll();}


    public Paciente obtenerPorId(long id){
        return repository.getReferenceById(id);
    }

    public Paciente modificar(Paciente paciente, long id){
        Paciente existingPaciente= repository.getReferenceById(id);
        existingPaciente.setNombre(paciente.getNombre());
        existingPaciente.setApellido(paciente.getApellido());
        existingPaciente.setDomicilio(paciente.getDomicilio());
        existingPaciente.setDni(paciente.getDni());
        existingPaciente.setFechaAlta(paciente.getFechaAlta());
        repository.save(existingPaciente);
        return existingPaciente;
    }


    public Paciente eliminarPaciente(long id){
        Paciente existingPaciente= repository.getReferenceById(id);
        repository.deleteById(id);
        return existingPaciente;}








}
