package com.gruposalinas.prueba_armando.service;

import com.gruposalinas.prueba_armando.dto.DomicilioDTO;
import com.gruposalinas.prueba_armando.dto.EmpleoDTO;
import com.gruposalinas.prueba_armando.dto.ProspectoDTO;
import com.gruposalinas.prueba_armando.dto.ProspectoResponseDTO;
import com.gruposalinas.prueba_armando.exception.ProspectoNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class ProspectoServiceTest {

    @Autowired
    private ProspectoService prospectoService;

    private ProspectoDTO prospectoDTO;

    @BeforeEach
    void setUp() {
        prospectoDTO = new ProspectoDTO();
        prospectoDTO.setNombres("Juan");
        prospectoDTO.setApellidos("Pérez García");
        prospectoDTO.setEmail("juan.perez@example.com");
        prospectoDTO.setFechaNacimiento(LocalDate.of(1990, 5, 15));
        prospectoDTO.setRfc("PEGJ900515ABC");
        prospectoDTO.setTelefono("5551234567");

        DomicilioDTO domicilio = new DomicilioDTO();
        domicilio.setCalle("Av. Reforma");
        domicilio.setNumero("123");
        domicilio.setColonia("Centro");
        domicilio.setCiudad("Ciudad de México");
        domicilio.setEstado("CDMX");
        domicilio.setPais("México");
        domicilio.setCodigoPostal("06000");
        prospectoDTO.setDomicilio(domicilio);

        List<EmpleoDTO> empleos = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            EmpleoDTO empleo = new EmpleoDTO();
            empleo.setNombreEmpresa("Empresa " + i);
            empleo.setFechaIngreso(LocalDate.of(2020 + i, 1, 1));
            empleo.setFechaSalida(i < 2 ? LocalDate.of(2020 + i + 1, 12, 31) : null);
            empleo.setIngresoMensual(15000.0 + (i * 1000));
            empleo.setGiroEmpresa("Tecnología");
            empleos.add(empleo);
        }
        prospectoDTO.setListaEmpleos(empleos);
    }

    @Test
    void testCreateProspecto() {
        ProspectoResponseDTO created = prospectoService.create(prospectoDTO);
        assertNotNull(created);
        assertNotNull(created.getId());
        assertEquals("Juan", created.getNombres());
        assertEquals(3, created.getListaEmpleos().size());
    }

    @Test
    void testCreateProspectoWithLessThan3Empleos() {
        prospectoDTO.getListaEmpleos().remove(0);
        assertThrows(IllegalArgumentException.class, () -> {
            prospectoService.create(prospectoDTO);
        });
    }

    @Test
    void testFindById() {
        ProspectoResponseDTO created = prospectoService.create(prospectoDTO);
        ProspectoResponseDTO found = prospectoService.findById(created.getId());
        assertNotNull(found);
        assertEquals(created.getId(), found.getId());
    }

    @Test
    void testFindByIdNotFound() {
        UUID nonExistentId = UUID.randomUUID();
        assertThrows(ProspectoNotFoundException.class, () -> {
            prospectoService.findById(nonExistentId);
        });
    }
}

