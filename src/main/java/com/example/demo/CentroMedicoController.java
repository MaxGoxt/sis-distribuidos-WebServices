package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/centrosmedicos")
public class CentroMedicoController {

    private final CentroMedicoRepository centroMedicoRepository;

    public CentroMedicoController(CentroMedicoRepository centroMedicoRepository) {
        this.centroMedicoRepository = centroMedicoRepository;
    }

    @GetMapping
    public List<CentroMedico> getAllCentros() {
        List<CentroMedico> centros = centroMedicoRepository.findAll();
        if (centros == null) {
            return new ArrayList<>();
        }
        return centros;
    }

    @PostMapping
    public ResponseEntity<CentroMedico> createCentro(@RequestBody CentroMedico centroMedico) {
        CentroMedico nuevo = centroMedicoRepository.save(centroMedico);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CentroMedico> getCentroById(@PathVariable Long id) {
        return centroMedicoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CentroMedico> updateCentro(@PathVariable Long id, @RequestBody CentroMedico data) {
        return centroMedicoRepository.findById(id).map(centro -> {
            centro.setNombre(data.getNombre());
            centro.setDireccion(data.getDireccion());
            centro.setTelefono(data.getTelefono());
            CentroMedico actualizado = centroMedicoRepository.save(centro);
            return ResponseEntity.ok(actualizado);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCentro(@PathVariable Long id) {
        CentroMedico centro = centroMedicoRepository.findById(id).orElse(null);

        if (centro == null) {
            return ResponseEntity.notFound().build();
        }

        if (centro.getPacientes() != null && !centro.getPacientes().isEmpty()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("No se puede eliminar el centro porque tiene pacientes asignados.");
        }

        centroMedicoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
