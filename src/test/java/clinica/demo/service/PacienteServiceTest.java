package clinica.demo.service;

import clinica.models.Domicilio;
import clinica.models.Paciente;
import clinica.security.service.PacienteService;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class PacienteServiceTest {
    @Autowired
    private PacienteService pacienteService;

    @Test
    @Order(1)
    public void guardarPacienteTest(){
        Paciente pacienteAGuardar= new Paciente((1L),"pablo","moya",("12.125"),(LocalDateTime.of(2022,12,13,23,59,59,59)),
                new Domicilio(1L,"mi casa","la costa","mar de ajo"));
        Paciente pacienteGuardado=pacienteService.agregar(pacienteAGuardar);
        assertEquals(1L,pacienteGuardado.getId());
    }
    @Test
    @Order(2)
    public void buscarPacientePorIdTest(){
        Long idABuscar=1L;
        Optional<Paciente> pacienteBuscado= Optional.of(pacienteService.obtenerPorId(idABuscar));
        assertNotNull(pacienteBuscado.get());
    }
    @Test
    @Order(3)
    public void listarPacientesTest(){
        List<Paciente> pacientes= pacienteService.Listar();
        Integer cantidadEsperada=1;
        assertEquals(cantidadEsperada,pacientes.size());
    }
    @Test
    @Order(4)
    public void actualizarPacienteTest(){
        Paciente pacienteAActualizar= new Paciente(1L,"pablo","moya"
                ,"123", LocalDateTime.of(2022,12,13,23,59,59,59),new Domicilio(1L,"mi casa","la costa","mar de ajo"));
        pacienteService.modificar(pacienteAActualizar, pacienteAActualizar.getId());
        Optional<Paciente> pacienteActualizado= Optional.of(pacienteService.obtenerPorId(1L));
        assertEquals("pablo",pacienteActualizado.get().getNombre());
    }
    @Test
    @Order(5)
    public void eliminarPacienteTest(){
        Long idAEliminar=1L;
        pacienteService.eliminarPaciente(idAEliminar);
        Optional<Paciente> pacienteEliminado= Optional.of(pacienteService.eliminarPaciente(idAEliminar));
        assertFalse(pacienteEliminado.isPresent());
    }
}