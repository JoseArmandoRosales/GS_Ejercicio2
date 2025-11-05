package com.gruposalinas.prueba_armando.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gruposalinas.prueba_armando.dto.DomicilioDTO;
import com.gruposalinas.prueba_armando.dto.EmpleoDTO;
import com.gruposalinas.prueba_armando.dto.ProspectoDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureWebMvc
@ActiveProfiles("test")
@Transactional
class ProspectoControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ObjectMapper objectMapper;

    private MockMvc mockMvc;

    private ProspectoDTO prospectoDTO;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

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
    void testCreateProspecto() throws Exception {
        mockMvc.perform(post("/api/prospectos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(prospectoDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.nombres").value("Juan"));
    }

    @Test
    void testCreateProspectoWithInvalidData() throws Exception {
        prospectoDTO.setEmail("invalid-email");
        mockMvc.perform(post("/api/prospectos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(prospectoDTO)))
                .andExpect(status().isBadRequest());
    }
}

