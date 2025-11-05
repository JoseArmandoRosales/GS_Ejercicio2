package com.gruposalinas.prueba_armando.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class ProspectoResponseDTO {
    private UUID id;
    private String nombres;
    private String apellidos;
    private String email;
    private LocalDate fechaNacimiento;
    private String rfc;
    private String telefono;
    private DomicilioResponseDTO domicilio;
    private List<EmpleoResponseDTO> listaEmpleos;

    public ProspectoResponseDTO() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public DomicilioResponseDTO getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(DomicilioResponseDTO domicilio) {
        this.domicilio = domicilio;
    }

    public List<EmpleoResponseDTO> getListaEmpleos() {
        return listaEmpleos;
    }

    public void setListaEmpleos(List<EmpleoResponseDTO> listaEmpleos) {
        this.listaEmpleos = listaEmpleos;
    }
}

