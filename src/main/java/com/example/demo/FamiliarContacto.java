package com.example.demo;

import jakarta.persistence.*;

@Entity
public class FamiliarContacto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String parentesco;
    private String telefono;

    public FamiliarContacto() {
    }

    public FamiliarContacto(String nombre, String parentesco, String telefono) {
        this.nombre = nombre;
        this.parentesco = parentesco;
        this.telefono = telefono;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getParentesco() {
        return parentesco;
    }

    public void setParentesco(String parentesco) {
        this.parentesco = parentesco;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
