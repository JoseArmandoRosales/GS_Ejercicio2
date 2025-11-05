package com.gruposalinas.prueba_armando.dto;

import java.time.LocalDate;
import java.util.UUID;

public class EmpleoResponseDTO {
    private UUID id;
    private String nombreEmpresa;
    private LocalDate fechaIngreso;
    private LocalDate fechaSalida;
    private Double ingresoMensual;
    private String giroEmpresa;

    public EmpleoResponseDTO() {
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

