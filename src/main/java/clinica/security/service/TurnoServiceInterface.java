package clinica.security.service;

import clinica.models.Domicilio;
import clinica.models.Odontologo;
import clinica.models.Paciente;
import clinica.models.Turno;
import clinica.playload.request.PacienteRequest;

import java.util.List;

public interface TurnoServiceInterface {

    Turno saveTurno(Turno turno) throws Exception;

    List<Turno> getAllTurnos();
    Turno getTurnoById(long id);
    Turno updateTurno(Turno turno,long id);
    Turno deleteTurno(long id);
    Odontologo saveOdontologo(Odontologo odontologo);

    List<Odontologo> getAllOdontologos();

    Odontologo getOdontologoById(long id);

    Odontologo updateOdontologo(Odontologo odontologo, long id);

    Odontologo deleteOdontologo(long id);
    Paciente savePaciente(Paciente paciente);
    List<Paciente> getAllPacientes();
    Paciente getPacienteById(long id);
    Paciente updatePaciente(Paciente paciente,long id);
    Paciente deletePaciente(long id);

    Domicilio saveDomicilio(PacienteRequest pacienteRequest);

    List<Domicilio> getAllDomicilios();

    Domicilio getDomicilioById(long id);

    Domicilio updateDomicilio(Domicilio domicilio, long id);

    Domicilio deleteDomicilio(long id);
}

