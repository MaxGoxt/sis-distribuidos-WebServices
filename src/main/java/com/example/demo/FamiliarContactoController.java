package com.example.demo;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/familiares")
public class FamiliarContactoController {

    private final FamiliarContactoRepository familiarContactoRepository;

    public FamiliarContactoController(FamiliarContactoRepository familiarContactoRepository) {
        this.familiarContactoRepository = familiarContactoRepository;
    }

    @GetMapping
    public List<FamiliarContacto> getAllFamiliares() {
        return familiarContactoRepository.findAll();
    }

    @PostMapping
    public FamiliarContacto createFamiliar(@RequestBody FamiliarContacto familiar) {
        return familiarContactoRepository.save(familiar);
    }

    @GetMapping("/{id}")
    public FamiliarContacto getFamiliarById(@PathVariable Long id) {
        return familiarContactoRepository.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public FamiliarContacto updateFamiliar(@PathVariable Long id, @RequestBody FamiliarContacto data) {
        return familiarContactoRepository.findById(id).map(familiar -> {
            familiar.setNombre(data.getNombre());
            familiar.setParentesco(data.getParentesco());
            familiar.setTelefono(data.getTelefono());
            return familiarContactoRepository.save(familiar);
        }).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deleteFamiliar(@PathVariable Long id) {
        familiarContactoRepository.deleteById(id);
    }
}

