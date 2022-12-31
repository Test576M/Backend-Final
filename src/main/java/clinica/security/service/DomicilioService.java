package clinica.security.service;


import clinica.models.Domicilio;
import clinica.models.Paciente;
import clinica.playload.request.PacienteRequest;
import clinica.repositories.DomicilioRepository;
import clinica.repositories.PacienteRepository;

import java.util.List;


public class DomicilioService {

    private DomicilioRepository domicilioRepository;
    private PacienteRepository pacienteRepository;

    public DomicilioService(DomicilioRepository domicilioRepository, PacienteRepository pacienteRepository) {
        this.domicilioRepository = domicilioRepository;
        this.pacienteRepository = pacienteRepository;
    }


    public Domicilio saveDomicilio(PacienteRequest pacienteRequest) {
        Paciente paciente = new Paciente();
        paciente.setNombre(pacienteRequest.getNombre());
        paciente.setApellido(pacienteRequest.getApellido());
        paciente.setDni(pacienteRequest.getDni());
        paciente.setFechaAlta(pacienteRequest.getFecha());
        Domicilio domicilio = new Domicilio(1L,"micasa","la costa","mar de ajo");
        domicilio.setCalle(pacienteRequest.getCalle());
        domicilio.setLocalidad(pacienteRequest.getLocalidad());
        domicilio.setCiudad(pacienteRequest.getCiudad());
        paciente.setDomicilio(domicilio);
        domicilio.setPaciente(paciente);
        pacienteRepository.save(paciente);
        return domicilio;
    }


    public List<Domicilio> getAllDomicilios() {
        return domicilioRepository.findAll();
    }


    public Domicilio getDomicilioById(long id) {
        return domicilioRepository.getReferenceById(id);
    }


    public Domicilio updateDomicilio(Domicilio domicilio, long id) {
        Domicilio existingDomicilio = domicilioRepository.getReferenceById(id);
        existingDomicilio.setCalle(domicilio.getCalle());
        existingDomicilio.setLocalidad(domicilio.getLocalidad());
        existingDomicilio.setCiudad(domicilio.getCiudad());
        domicilioRepository.save(existingDomicilio);
        return existingDomicilio;
    }



    public Domicilio deleteDomicilio(long id) {
        Domicilio existingDomicilio = domicilioRepository.getReferenceById(id);
        domicilioRepository.deleteById(id);
        return existingDomicilio;
    }
}
