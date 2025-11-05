package com.gruposalinas.prueba_armando.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

public class EmpleoDTO {
    
    @NotBlank(message = "El nombre de la empresa es obligatorio")
    @Size(max = 200, message = "El nombre de la empresa no puede exceder 200 caracteres")
    private String nombreEmpresa;
    
    @NotNull(message = "La fecha de ingreso es obligatoria")
    private LocalDate fechaIngreso;
    
    private LocalDate fechaSalida;
    
    @NotNull(message = "El ingreso mensual es obligatorio")
    @Positive(message = "El ingreso mensual debe ser positivo")
    private Double ingresoMensual;
    
    @Size(max = 200, message = "El giro de la empresa no puede exceder 200 caracteres")
    private String giroEmpresa;

    public EmpleoDTO() {
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

