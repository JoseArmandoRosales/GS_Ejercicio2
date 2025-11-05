package com.gruposalinas.prueba_armando.mapper;

import com.gruposalinas.prueba_armando.domain.Domicilio;
import com.gruposalinas.prueba_armando.domain.Empleo;
import com.gruposalinas.prueba_armando.domain.Prospecto;
import com.gruposalinas.prueba_armando.dto.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class ProspectoMapper {

    public Prospecto toDomain(ProspectoDTO dto) {
        Prospecto prospecto = new Prospecto();
        prospecto.setNombres(dto.getNombres());
        prospecto.setApellidos(dto.getApellidos());
        prospecto.setEmail(dto.getEmail());
        prospecto.setFechaNacimiento(dto.getFechaNacimiento());
        prospecto.setRfc(dto.getRfc());
        prospecto.setTelefono(dto.getTelefono());
        
        if (dto.getDomicilio() != null) {
            prospecto.setDomicilio(toDomain(dto.getDomicilio()));
        }
        
        if (dto.getListaEmpleos() != null) {
            prospecto.setListaEmpleos(
                dto.getListaEmpleos().stream()
                    .map(this::toDomain)
                    .collect(Collectors.toList())
            );
        }
        
        return prospecto;
    }

    public Domicilio toDomain(DomicilioDTO dto) {
        Domicilio domicilio = new Domicilio();
        domicilio.setCalle(dto.getCalle());
        domicilio.setNumero(dto.getNumero());
        domicilio.setColonia(dto.getColonia());
        domicilio.setCiudad(dto.getCiudad());
        domicilio.setEstado(dto.getEstado());
        domicilio.setPais(dto.getPais());
        domicilio.setCodigoPostal(dto.getCodigoPostal());
        return domicilio;
    }

    public Empleo toDomain(EmpleoDTO dto) {
        Empleo empleo = new Empleo();
        empleo.setNombreEmpresa(dto.getNombreEmpresa());
        empleo.setFechaIngreso(dto.getFechaIngreso());
        empleo.setFechaSalida(dto.getFechaSalida());
        empleo.setIngresoMensual(dto.getIngresoMensual());
        empleo.setGiroEmpresa(dto.getGiroEmpresa());
        return empleo;
    }

    public ProspectoResponseDTO toResponseDTO(Prospecto prospecto) {
        ProspectoResponseDTO dto = new ProspectoResponseDTO();
        dto.setId(prospecto.getId());
        dto.setNombres(prospecto.getNombres());
        dto.setApellidos(prospecto.getApellidos());
        dto.setEmail(prospecto.getEmail());
        dto.setFechaNacimiento(prospecto.getFechaNacimiento());
        dto.setRfc(prospecto.getRfc());
        dto.setTelefono(prospecto.getTelefono());
        
        if (prospecto.getDomicilio() != null) {
            dto.setDomicilio(toResponseDTO(prospecto.getDomicilio()));
        }
        
        if (prospecto.getListaEmpleos() != null) {
            dto.setListaEmpleos(
                prospecto.getListaEmpleos().stream()
                    .map(this::toResponseDTO)
                    .collect(Collectors.toList())
            );
        }
        
        return dto;
    }

    public DomicilioResponseDTO toResponseDTO(Domicilio domicilio) {
        DomicilioResponseDTO dto = new DomicilioResponseDTO();
        dto.setId(domicilio.getId());
        dto.setCalle(domicilio.getCalle());
        dto.setNumero(domicilio.getNumero());
        dto.setColonia(domicilio.getColonia());
        dto.setCiudad(domicilio.getCiudad());
        dto.setEstado(domicilio.getEstado());
        dto.setPais(domicilio.getPais());
        dto.setCodigoPostal(domicilio.getCodigoPostal());
        return dto;
    }

    public EmpleoResponseDTO toResponseDTO(Empleo empleo) {
        EmpleoResponseDTO dto = new EmpleoResponseDTO();
        dto.setId(empleo.getId());
        dto.setNombreEmpresa(empleo.getNombreEmpresa());
        dto.setFechaIngreso(empleo.getFechaIngreso());
        dto.setFechaSalida(empleo.getFechaSalida());
        dto.setIngresoMensual(empleo.getIngresoMensual());
        dto.setGiroEmpresa(empleo.getGiroEmpresa());
        return dto;
    }

    public List<ProspectoResponseDTO> toResponseDTOList(List<Prospecto> prospectos) {
        return prospectos.stream()
            .map(this::toResponseDTO)
            .collect(Collectors.toList());
    }
}

