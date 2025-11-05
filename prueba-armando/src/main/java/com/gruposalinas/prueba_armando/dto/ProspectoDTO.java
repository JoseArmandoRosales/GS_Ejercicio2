package com.gruposalinas.prueba_armando.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

public class ProspectoDTO {
    
    @NotBlank(message = "Los nombres son obligatorios")
    @Size(max = 100, message = "Los nombres no pueden exceder 100 caracteres")
    private String nombres;
    
    @NotBlank(message = "Los apellidos son obligatorios")
    @Size(max = 100, message = "Los apellidos no pueden exceder 100 caracteres")
    private String apellidos;
    
    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El email debe tener un formato válido")
    @Size(max = 100, message = "El email no puede exceder 100 caracteres")
    private String email;
    
    @NotNull(message = "La fecha de nacimiento es obligatoria")
    private LocalDate fechaNacimiento;
    
    @NotBlank(message = "El RFC es obligatorio")
    @Pattern(regexp = "^[A-Z&Ñ]{3,4}\\d{6}[A-Z\\d]{3}$", message = "El RFC debe tener un formato válido")
    @Size(max = 13, message = "El RFC no puede exceder 13 caracteres")
    private String rfc;
    
    @Size(max = 20, message = "El teléfono no puede exceder 20 caracteres")
    private String telefono;
    
    @Valid
    @NotNull(message = "El domicilio es obligatorio")
    private DomicilioDTO domicilio;
    
    @Valid
    @NotNull(message = "La lista de empleos es obligatoria")
    @Size(min = 3, message = "Debe tener al menos 3 empleos")
    private List<EmpleoDTO> listaEmpleos;

    public ProspectoDTO() {
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

    public DomicilioDTO getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(DomicilioDTO domicilio) {
        this.domicilio = domicilio;
    }

    public List<EmpleoDTO> getListaEmpleos() {
        return listaEmpleos;
    }

    public void setListaEmpleos(List<EmpleoDTO> listaEmpleos) {
        this.listaEmpleos = listaEmpleos;
    }
}

