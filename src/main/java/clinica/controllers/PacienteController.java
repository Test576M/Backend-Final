package clinica.controllers;

import clinica.models.Paciente;
import clinica.security.service.TurnoServiceInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin (origins = "*")
@RestController
@RequestMapping(("/api/pacientes"))
public class PacienteController {

    private TurnoServiceInterface turnoServiceInterface;

    public PacienteController(TurnoServiceInterface turnoServiceInterface) {
        this.turnoServiceInterface = turnoServiceInterface;
    }


    @PostMapping()
    public ResponseEntity<Paciente> savePaciente(@RequestBody Paciente paciente){
        return new ResponseEntity<Paciente>(turnoServiceInterface.savePaciente(paciente), HttpStatus.CREATED);
    };
    @GetMapping
    public List<Paciente> getAllPacientes(){
        return turnoServiceInterface.getAllPacientes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> getPacienteById(@PathVariable long pacienteId){
        return new ResponseEntity<Paciente>(turnoServiceInterface.getPacienteById(pacienteId), HttpStatus.OK);
    }
    @PutMapping()
    public ResponseEntity<Paciente> updatePaciente(@PathVariable long pacienteId,@RequestBody Paciente paciente){
        return new ResponseEntity<Paciente>(turnoServiceInterface.updatePaciente(paciente, pacienteId), HttpStatus.OK);}



    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePaciente(@PathVariable long pacienteId){
        turnoServiceInterface.deletePaciente(pacienteId);
        return new ResponseEntity<String>("Paciente deleted succesfully!.", HttpStatus.OK);
    }

}
