package clinica.controllers;


import clinica.models.Domicilio;
import clinica.security.service.DomicilioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(("/api/domicilios"))

public class DomicilioController {

    private DomicilioService domicilioService;

    public DomicilioController(DomicilioService domicilioService) {this.domicilioService = domicilioService;}

    @GetMapping("/")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    public List<Domicilio> getAllDomicilios(){
        return domicilioService.getAllDomicilios();
    }

    @GetMapping("{id}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Domicilio> getDomicilioById(@PathVariable("id") long domicilioId){
        return new ResponseEntity<Domicilio>(domicilioService.getDomicilioById(domicilioId), HttpStatus.OK);
    }
    @PutMapping("{id}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Domicilio> updateDomicilio(@PathVariable("id") long domicilioId,@RequestBody Domicilio domicilio){
        return new ResponseEntity<Domicilio>(domicilioService.updateDomicilio(domicilio, domicilioId), HttpStatus.OK);}

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteDomicilioBy(@PathVariable("id") long domicilioId){
        domicilioService.deleteDomicilio(domicilioId);
        return new ResponseEntity<String>("Domicilio deleted succesfully!.", HttpStatus.OK);
    }

}
