package com.gruposalinas.prueba_armando.service;

import com.gruposalinas.prueba_armando.domain.Prospecto;
import com.gruposalinas.prueba_armando.dto.ProspectoDTO;
import com.gruposalinas.prueba_armando.dto.ProspectoResponseDTO;
import com.gruposalinas.prueba_armando.exception.ProspectoNotFoundException;
import com.gruposalinas.prueba_armando.mapper.ProspectoMapper;
import com.gruposalinas.prueba_armando.repository.ProspectoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class ProspectoServiceImpl implements ProspectoService {

    private final ProspectoRepository prospectoRepository;
    private final ProspectoMapper prospectoMapper;

    public ProspectoServiceImpl(ProspectoRepository prospectoRepository, ProspectoMapper prospectoMapper) {
        this.prospectoRepository = prospectoRepository;
        this.prospectoMapper = prospectoMapper;
    }

    @Override
    @Transactional
    public ProspectoResponseDTO create(ProspectoDTO prospectoDTO) {
        // Validar que tenga al menos 3 empleos
        if (prospectoDTO.getListaEmpleos() == null || prospectoDTO.getListaEmpleos().size() < 3) {
            throw new IllegalArgumentException("Un prospecto debe tener al menos 3 empleos en su historial");
        }

        // Validar que el email no exista
        if (prospectoRepository.existsByEmail(prospectoDTO.getEmail())) {
            throw new IllegalArgumentException("Ya existe un prospecto con el email: " + prospectoDTO.getEmail());
        }

        Prospecto prospecto = prospectoMapper.toDomain(prospectoDTO);
        prospecto.setId(UUID.randomUUID());
        
        Prospecto savedProspecto = prospectoRepository.save(prospecto);
        return prospectoMapper.toResponseDTO(savedProspecto);
    }

    @Override
    @Transactional
    public ProspectoResponseDTO update(UUID id, ProspectoDTO prospectoDTO) {
        // Validar que el prospecto exista
        if (!prospectoRepository.existsById(id)) {
            throw new ProspectoNotFoundException("Prospecto no encontrado con ID: " + id);
        }

        // Validar que tenga al menos 3 empleos
        if (prospectoDTO.getListaEmpleos() == null || prospectoDTO.getListaEmpleos().size() < 3) {
            throw new IllegalArgumentException("Un prospecto debe tener al menos 3 empleos en su historial");
        }

        // Validar que si cambia el email, no exista otro prospecto con ese email
        Prospecto existingProspecto = prospectoRepository.findById(id)
            .orElseThrow(() -> new ProspectoNotFoundException("Prospecto no encontrado con ID: " + id));
        
        if (!existingProspecto.getEmail().equals(prospectoDTO.getEmail()) && 
            prospectoRepository.existsByEmail(prospectoDTO.getEmail())) {
            throw new IllegalArgumentException("Ya existe un prospecto con el email: " + prospectoDTO.getEmail());
        }

        Prospecto prospecto = prospectoMapper.toDomain(prospectoDTO);
        prospecto.setId(id);
        
        Prospecto updatedProspecto = prospectoRepository.update(prospecto);
        return prospectoMapper.toResponseDTO(updatedProspecto);
    }

    @Override
    public ProspectoResponseDTO findById(UUID id) {
        Prospecto prospecto = prospectoRepository.findById(id)
            .orElseThrow(() -> new ProspectoNotFoundException("Prospecto no encontrado con ID: " + id));
        return prospectoMapper.toResponseDTO(prospecto);
    }

    @Override
    @Transactional
    public void deleteById(UUID id) {
        if (!prospectoRepository.existsById(id)) {
            throw new ProspectoNotFoundException("Prospecto no encontrado con ID: " + id);
        }
        prospectoRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteByEmail(String email) {
        if (!prospectoRepository.existsByEmail(email)) {
            throw new ProspectoNotFoundException("Prospecto no encontrado con email: " + email);
        }
        prospectoRepository.deleteByEmail(email);
    }

    @Override
    public List<ProspectoResponseDTO> findByEmpresa(String nombreEmpresa) {
        List<Prospecto> prospectos = prospectoRepository.findByEmpresa(nombreEmpresa);
        return prospectoMapper.toResponseDTOList(prospectos);
    }

    @Override
    public List<ProspectoResponseDTO> findByIngresosMinimos(Double montoMinimo) {
        if (montoMinimo == null || montoMinimo < 0) {
            throw new IllegalArgumentException("El monto mínimo debe ser un valor positivo");
        }
        List<Prospecto> prospectos = prospectoRepository.findByIngresosMinimos(montoMinimo);
        return prospectoMapper.toResponseDTOList(prospectos);
    }

    @Override
    public List<ProspectoResponseDTO> findByEmpleoVigente() {
        List<Prospecto> prospectos = prospectoRepository.findByEmpleoVigente();
        return prospectoMapper.toResponseDTOList(prospectos);
    }
}

