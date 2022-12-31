package clinica.controllers;

import clinica.models.Turno;
import clinica.playload.request.TurnoRequest;
import clinica.security.service.TurnoServiceInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(("/api/turnos"))
public class TurnoController {

    private TurnoServiceInterface turnoServiceInterface;

    public TurnoController(TurnoServiceInterface turnoServiceInterface) {
        this.turnoServiceInterface = turnoServiceInterface;
    }


    @PostMapping()
    public ResponseEntity<String> saveTurno(@RequestBody TurnoRequest turnoRequest) throws Exception {
        Turno messageValue = turnoServiceInterface.saveTurno(Turno.from(turnoRequest));return new ResponseEntity<>(messageValue.message(messageValue) , HttpStatus.CREATED);

    };
    @GetMapping
    public List<Turno> getAllTurnos(){
        return turnoServiceInterface.getAllTurnos();
    }

    @GetMapping("{id}")
    public ResponseEntity<Turno> getTurnoById(@PathVariable long turnoId){
        return new ResponseEntity<Turno>(turnoServiceInterface.getTurnoById(turnoId), HttpStatus.OK);
    }
    @PutMapping("{id}")
    public ResponseEntity<Turno> updateTurno(@PathVariable long turnoId,@RequestBody Turno turno){
        return new ResponseEntity<Turno>(turnoServiceInterface.updateTurno(turno, turnoId), HttpStatus.OK);}

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteTurnoBy(@PathVariable long turnoId){
        turnoServiceInterface.deleteTurno(turnoId);
        return new ResponseEntity<String>("Turno deleted succesfully!.", HttpStatus.OK);
    }

}
