package com.gruposalinas.prueba_armando.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Prospecto {
    private UUID id;
    private String nombres;
    private String apellidos;
    private String email;
    private LocalDate fechaNacimiento;
    private String rfc;
    private String telefono;
    private Domicilio domicilio;
    private List<Empleo> listaEmpleos;

    public Prospecto() {
        this.listaEmpleos = new ArrayList<>();
    }

    public Prospecto(UUID id, String nombres, String apellidos, String email, 
                     LocalDate fechaNacimiento, String rfc, String telefono) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.email = email;
        this.fechaNacimiento = fechaNacimiento;
        this.rfc = rfc;
        this.telefono = telefono;
        this.listaEmpleos = new ArrayList<>();
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

    public Domicilio getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(Domicilio domicilio) {
        this.domicilio = domicilio;
    }

    public List<Empleo> getListaEmpleos() {
        return listaEmpleos;
    }

    public void setListaEmpleos(List<Empleo> listaEmpleos) {
        this.listaEmpleos = listaEmpleos;
    }
}

