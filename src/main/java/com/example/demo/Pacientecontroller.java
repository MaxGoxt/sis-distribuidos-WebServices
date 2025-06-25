package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
 import com.example.demo.dto.pacienteDTO;

import java.util.ArrayList;
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
public List<pacienteDTO> getAllPacientes() {
    List<Paciente> pacientes = pacienteRepository.findAll();
    List<pacienteDTO> resultado = new ArrayList<>();

    for (Paciente p : pacientes) {
        Long centroId = p.getCentroMedico() != null ? p.getCentroMedico().getId() : null;
        pacienteDTO dto = new pacienteDTO(
            p.getId(),
            p.getNombre(),
            p.getApellido(),
            p.getDocumento(),
            p.getFechaNacimiento(),
            centroId
        );
        resultado.add(dto);
    }

    return resultado;
}


    @PostMapping
    public ResponseEntity<Paciente> createPaciente(@RequestBody Paciente paciente) {
        if (paciente.getCentroMedico() != null && paciente.getCentroMedico().getId() != null) {
            CentroMedico centro = centroMedicoRepository.findById(paciente.getCentroMedico().getId()).orElse(null);
            if (centro == null) {
                return ResponseEntity.badRequest().build(); // Centro no encontrado
            }
            paciente.setCentroMedico(centro); // Asignamos el objeto completo
        } else {
            return ResponseEntity.badRequest().build(); // El ID del centro es obligatorio
        }

        Paciente nuevo = pacienteRepository.save(paciente);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> getPacienteById(@PathVariable Long id) {
        return pacienteRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Paciente> updatePaciente(@PathVariable Long id, @RequestBody Paciente data) {
        return pacienteRepository.findById(id).map(paciente -> {
            paciente.setNombre(data.getNombre());
            paciente.setApellido(data.getApellido());
            paciente.setDocumento(data.getDocumento());
            paciente.setFechaNacimiento(data.getFechaNacimiento());

            if (data.getCentroMedico() != null && data.getCentroMedico().getId() != null) {
                CentroMedico centro = centroMedicoRepository.findById(data.getCentroMedico().getId()).orElse(null);
                if (centro != null) {
                    paciente.setCentroMedico(centro);
                }
            }

            Paciente actualizado = pacienteRepository.save(paciente);
            return ResponseEntity.ok(actualizado);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePaciente(@PathVariable Long id) {
        if (!pacienteRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        pacienteRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

  
}
