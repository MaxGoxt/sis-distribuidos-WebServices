package com.example.demo;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class Pacientecontroller {

    private final PacienteRepository pacienteRepository;

    public Pacientecontroller(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    @GetMapping
    public List<Paciente> getAllPacientes() {
        return pacienteRepository.findAll();
    }

    @PostMapping
    public Paciente createPaciente(@RequestBody Paciente paciente) {
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
        return pacienteRepository.save(paciente);
    }).orElse(null);
}


    @DeleteMapping("/{id}")
    public void deletePaciente(@PathVariable Long id) {
        pacienteRepository.deleteById(id);
    }
}
