package com.gruposalinas.prueba_armando.repository;

import com.gruposalinas.prueba_armando.domain.Prospecto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProspectoRepository {
    
    Prospecto save(Prospecto prospecto);
    
    Optional<Prospecto> findById(UUID id);
    
    Optional<Prospecto> findByEmail(String email);
    
    void deleteById(UUID id);
    
    void deleteByEmail(String email);
    
    List<Prospecto> findByEmpresa(String nombreEmpresa);
    
    List<Prospecto> findByIngresosMinimos(Double montoMinimo);
    
    List<Prospecto> findByEmpleoVigente();
    
    boolean existsById(UUID id);
    
    boolean existsByEmail(String email);
    
    Prospecto update(Prospecto prospecto);
}

