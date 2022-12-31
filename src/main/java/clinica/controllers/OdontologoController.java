package clinica.controllers;

import clinica.models.Odontologo;
import clinica.security.service.TurnoServiceInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping(("/api/odontologos"))

public class OdontologoController {

    private TurnoServiceInterface turnoServiceInterface;

    public OdontologoController(TurnoServiceInterface turnoServiceInterface) {this.turnoServiceInterface = turnoServiceInterface;}

    @PostMapping()
    public ResponseEntity<Odontologo> saveOdontologo(@RequestBody Odontologo odontologo){
        return new ResponseEntity<Odontologo>(turnoServiceInterface.saveOdontologo(odontologo), HttpStatus.CREATED);
    };
    @GetMapping
    public List<Odontologo> getAllOdontologos(){
        return turnoServiceInterface.getAllOdontologos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Odontologo> getOdontologoById(@PathVariable("id") long odontologoId){
        return new ResponseEntity<Odontologo>(turnoServiceInterface.getOdontologoById(odontologoId), HttpStatus.OK);
    }
    @PutMapping()
    public ResponseEntity<Odontologo> updateOdontologo(@PathVariable("id") long odontologoId,@RequestBody Odontologo odontologo){
        return new ResponseEntity<Odontologo>(turnoServiceInterface.updateOdontologo(odontologo, odontologoId), HttpStatus.OK);}

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOdontologoBy(@PathVariable("id") long odontologoId){
        turnoServiceInterface.deleteOdontologo(odontologoId);
        return new ResponseEntity<String>("Odontologo deleted succesfully!.", HttpStatus.OK);
    }
}
