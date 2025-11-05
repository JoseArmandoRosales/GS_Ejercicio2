package com.gruposalinas.prueba_armando.service;

import com.gruposalinas.prueba_armando.dto.ProspectoDTO;
import com.gruposalinas.prueba_armando.dto.ProspectoResponseDTO;

import java.util.List;
import java.util.UUID;

public interface ProspectoService {
    
    ProspectoResponseDTO create(ProspectoDTO prospectoDTO);
    
    ProspectoResponseDTO update(UUID id, ProspectoDTO prospectoDTO);
    
    ProspectoResponseDTO findById(UUID id);
    
    void deleteById(UUID id);
    
    void deleteByEmail(String email);
    
    List<ProspectoResponseDTO> findByEmpresa(String nombreEmpresa);
    
    List<ProspectoResponseDTO> findByIngresosMinimos(Double montoMinimo);
    
    List<ProspectoResponseDTO> findByEmpleoVigente();
}

