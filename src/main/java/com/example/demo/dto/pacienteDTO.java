// src/main/java/com/example/demo/dto/PacienteDTO.java
package com.example.demo.dto;

import java.time.LocalDate;

public class pacienteDTO {
    private Long id;
    private String nombre;
    private String apellido;
    private String documento;
    private LocalDate fechaNacimiento;
    private Long centroMedicoId;

    public pacienteDTO() {}

    public pacienteDTO(Long id, String nombre, String apellido, String documento, LocalDate fechaNacimiento, Long centroMedicoId) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.documento = documento;
        this.fechaNacimiento = fechaNacimiento;
        this.centroMedicoId = centroMedicoId;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public String getDocumento() { return documento; }
    public LocalDate getFechaNacimiento() { return fechaNacimiento; }
    public Long getCentroMedicoId() { return centroMedicoId; }

    public void setId(Long id) { this.id = id; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setApellido(String apellido) { this.apellido = apellido; }
    public void setDocumento(String documento) { this.documento = documento; }
    public void setFechaNacimiento(LocalDate fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }
    public void setCentroMedicoId(Long centroMedicoId) { this.centroMedicoId = centroMedicoId; }
}
