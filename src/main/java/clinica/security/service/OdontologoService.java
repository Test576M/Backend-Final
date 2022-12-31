package clinica.security.service;


import clinica.models.Odontologo;
import clinica.repositories.OdontologoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OdontologoService {

    public OdontologoService(OdontologoRepository odontologoRepository){
        this.repository = odontologoRepository;
    }
    private final OdontologoRepository repository;



    public Odontologo agregar (Odontologo odontologo){
        return repository.save(odontologo);
    }


    public List<Odontologo> Listar(){return repository.findAll();}


    public Odontologo obtenerPorId(long id){
        return repository.getReferenceById(id);
    }

    public Odontologo modificar(Odontologo odontologo, long id){
        Odontologo existingOdontologo= repository.getReferenceById(id);
        existingOdontologo.setNombre(odontologo.getNombre());
        existingOdontologo.setApellido(odontologo.getApellido());
        existingOdontologo.setMatricula(odontologo.getMatricula());
        repository.save(existingOdontologo);
        return existingOdontologo;
    }


    public Odontologo eliminarOdontologo(long id){
        Odontologo existingOdontologo= repository.getReferenceById(id);
        repository.deleteById(id);
        return existingOdontologo;}


    }

