package com.example.demo;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class Pacientecontroller {

    private final PacienteRepository pacienteRepository;
    private final CentroMedicoRepository centroMedicoRepository;

    public Pacientecontroller(PacienteRepository pacienteRepository, CentroMedicoRepository centroMedicoRepository) {
        this.pacienteRepository = pacienteRepository;
        this.centroMedicoRepository = centroMedicoRepository;
    }

    @GetMapping
    public List<Paciente> getAllPacientes() {
        return pacienteRepository.findAll();
    }

    @PostMapping
    public Paciente crearPaciente(@RequestBody Paciente paciente) {
        if (paciente.getCentroMedico() != null && paciente.getCentroMedico().getId() != null) {
            CentroMedico centro = this.centroMedicoRepository
                .findById(paciente.getCentroMedico().getId())
                .orElseThrow(() -> new RuntimeException("Centro médico no encontrado"));
            paciente.setCentroMedico(centro);
        }
        System.out.println("Centro asociado: " + paciente.getCentroMedico().getNombre());

        return pacienteRepository.save(paciente);
    }


    @GetMapping("/{id}")
    public Paciente getPacienteById(@PathVariable Long id) {
        return pacienteRepository.findById(id).orElse(null);
    }

   @PutMapping("/{id}")
public Paciente updatePaciente(@PathVariable Long id, @RequestBody Paciente pacienteData) {
    return pacienteRepository.findById(id).map(paciente -> {
        paciente.setNombre(pacienteData.getNombre());
        paciente.setApellido(pacienteData.getApellido());
        paciente.setDocumento(pacienteData.getDocumento());
        paciente.setFechaNacimiento(pacienteData.getFechaNacimiento());
        paciente.setCentroMedico(pacienteData.getCentroMedico());
        return pacienteRepository.save(paciente);
    }).orElse(null);
}


    @DeleteMapping("/{id}")
    public void deletePaciente(@PathVariable Long id) {
        pacienteRepository.deleteById(id);
    }
}
