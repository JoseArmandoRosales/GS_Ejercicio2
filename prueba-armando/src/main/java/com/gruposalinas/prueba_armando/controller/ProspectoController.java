package com.gruposalinas.prueba_armando.controller;

import com.gruposalinas.prueba_armando.dto.ProspectoDTO;
import com.gruposalinas.prueba_armando.dto.ProspectoResponseDTO;
import com.gruposalinas.prueba_armando.service.ProspectoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/prospectos")
@Tag(name = "Prospectos", description = "API para gestión de prospectos de empleo")
public class ProspectoController {

    private final ProspectoService prospectoService;

    public ProspectoController(ProspectoService prospectoService) {
        this.prospectoService = prospectoService;
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo prospecto", description = "Crea un nuevo prospecto con sus datos personales, domicilio y al menos 3 empleos")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Prospecto creado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos o prospecto debe tener al menos 3 empleos"),
        @ApiResponse(responseCode = "409", description = "Ya existe un prospecto con el email proporcionado")
    })
    public ResponseEntity<ProspectoResponseDTO> create(@Valid @RequestBody ProspectoDTO prospectoDTO) {
        ProspectoResponseDTO created = prospectoService.create(prospectoDTO);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un prospecto", description = "Actualiza los datos de un prospecto existente, incluyendo datos personales, domicilio y empleos")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Prospecto actualizado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos o UUID inválido en el parámetro de ruta"),
        @ApiResponse(responseCode = "404", description = "Prospecto no encontrado")
    })
    public ResponseEntity<ProspectoResponseDTO> update(
            @Parameter(description = "ID del prospecto a actualizar (debe ser un UUID válido, formato: xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx)", 
                       example = "550e8400-e29b-41d4-a716-446655440001", 
                       required = true) @PathVariable UUID id,
            @Valid @RequestBody ProspectoDTO prospectoDTO) {
        ProspectoResponseDTO updated = prospectoService.update(id, prospectoDTO);
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar prospecto por ID", description = "Obtiene un prospecto por su identificador único (UUID)")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Prospecto encontrado"),
        @ApiResponse(responseCode = "400", description = "UUID inválido en el parámetro de ruta"),
        @ApiResponse(responseCode = "404", description = "Prospecto no encontrado")
    })
    public ResponseEntity<ProspectoResponseDTO> findById(
            @Parameter(description = "ID del prospecto (debe ser un UUID válido, formato: xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx)", 
                       example = "550e8400-e29b-41d4-a716-446655440001", 
                       required = true) @PathVariable UUID id) {
        ProspectoResponseDTO prospecto = prospectoService.findById(id);
        return ResponseEntity.ok(prospecto);
    }

    @GetMapping("/empresa/{nombreEmpresa}")
    @Operation(summary = "Listar prospectos por empresa", description = "Obtiene todos los prospectos que hayan trabajado para una empresa específica")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de prospectos obtenida exitosamente")
    })
    public ResponseEntity<List<ProspectoResponseDTO>> findByEmpresa(
            @Parameter(description = "Nombre de la empresa") @PathVariable String nombreEmpresa) {
        List<ProspectoResponseDTO> prospectos = prospectoService.findByEmpresa(nombreEmpresa);
        return ResponseEntity.ok(prospectos);
    }

    @GetMapping("/ingresos-minimos/{monto}")
    @Operation(summary = "Listar prospectos con ingresos mínimos", description = "Obtiene todos los prospectos con ingresos superiores al monto especificado en cualquiera de sus empleos")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de prospectos obtenida exitosamente"),
        @ApiResponse(responseCode = "400", description = "Monto inválido")
    })
    public ResponseEntity<List<ProspectoResponseDTO>> findByIngresosMinimos(
            @Parameter(description = "Monto mínimo de ingresos") @PathVariable Double monto) {
        List<ProspectoResponseDTO> prospectos = prospectoService.findByIngresosMinimos(monto);
        return ResponseEntity.ok(prospectos);
    }

    @GetMapping("/empleo-vigente")
    @Operation(summary = "Listar prospectos con empleo vigente", description = "Obtiene todos los prospectos cuyo último empleo esté vigente (sin fecha de salida)")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de prospectos obtenida exitosamente")
    })
    public ResponseEntity<List<ProspectoResponseDTO>> findByEmpleoVigente() {
        List<ProspectoResponseDTO> prospectos = prospectoService.findByEmpleoVigente();
        return ResponseEntity.ok(prospectos);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar prospecto por ID", description = "Elimina un prospecto y todos sus datos relacionados (domicilio y empleos) por su identificador único (UUID)")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Prospecto eliminado exitosamente"),
        @ApiResponse(responseCode = "400", description = "UUID inválido en el parámetro de ruta"),
        @ApiResponse(responseCode = "404", description = "Prospecto no encontrado")
    })
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "ID del prospecto a eliminar (debe ser un UUID válido, formato: xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx)", 
                       example = "550e8400-e29b-41d4-a716-446655440001", 
                       required = true) @PathVariable UUID id) {
        prospectoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/email/{email}")
    @Operation(summary = "Eliminar prospecto por email", description = "Elimina un prospecto y todos sus datos relacionados (domicilio y empleos) por su correo electrónico")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Prospecto eliminado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Prospecto no encontrado")
    })
    public ResponseEntity<Void> deleteByEmail(
            @Parameter(description = "Email del prospecto a eliminar") @PathVariable String email) {
        prospectoService.deleteByEmail(email);
        return ResponseEntity.noContent().build();
    }
}

