package com.gruposalinas.prueba_armando.domain;

import java.time.LocalDate;
import java.util.UUID;

public class Empleo {
    private UUID id;
    private String nombreEmpresa;
    private LocalDate fechaIngreso;
    private LocalDate fechaSalida;
    private Double ingresoMensual;
    private String giroEmpresa;

    public Empleo() {
    }

    public Empleo(UUID id, String nombreEmpresa, LocalDate fechaIngreso, 
                  LocalDate fechaSalida, Double ingresoMensual, String giroEmpresa) {
        this.id = id;
        this.nombreEmpresa = nombreEmpresa;
        this.fechaIngreso = fechaIngreso;
        this.fechaSalida = fechaSalida;
        this.ingresoMensual = ingresoMensual;
        this.giroEmpresa = giroEmpresa;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public LocalDate getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(LocalDate fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public Double getIngresoMensual() {
        return ingresoMensual;
    }

    public void setIngresoMensual(Double ingresoMensual) {
        this.ingresoMensual = ingresoMensual;
    }

    public String getGiroEmpresa() {
        return giroEmpresa;
    }

    public void setGiroEmpresa(String giroEmpresa) {
        this.giroEmpresa = giroEmpresa;
    }
}

