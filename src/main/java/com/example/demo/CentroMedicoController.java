package com.example.demo;

import org.springframework.web.bind.annotation.*;
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
        return centroMedicoRepository.findAll();
    }

    @PostMapping
    public CentroMedico createCentro(@RequestBody CentroMedico centroMedico) {
        return centroMedicoRepository.save(centroMedico);
    }

    @GetMapping("/{id}")
    public CentroMedico getCentroById(@PathVariable Long id) {
        return centroMedicoRepository.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public CentroMedico updateCentro(@PathVariable Long id, @RequestBody CentroMedico data) {
        return centroMedicoRepository.findById(id).map(centro -> {
            centro.setNombre(data.getNombre());
            centro.setDireccion(data.getDireccion());
            centro.setTelefono(data.getTelefono());
            return centroMedicoRepository.save(centro);
        }).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deleteCentro(@PathVariable Long id) {
        centroMedicoRepository.deleteById(id);
    }
}
