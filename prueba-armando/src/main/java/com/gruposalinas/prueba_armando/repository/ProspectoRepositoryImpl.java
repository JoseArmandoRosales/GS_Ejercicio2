package com.gruposalinas.prueba_armando.repository;

import com.gruposalinas.prueba_armando.domain.Domicilio;
import com.gruposalinas.prueba_armando.domain.Empleo;
import com.gruposalinas.prueba_armando.domain.Prospecto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Types;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class ProspectoRepositoryImpl implements ProspectoRepository {

    private final JdbcTemplate jdbcTemplate;

    public ProspectoRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public Prospecto save(Prospecto prospecto) {
        if (prospecto.getId() == null) {
            prospecto.setId(UUID.randomUUID());
        }

        // Insertar prospecto
        String sqlProspecto = "INSERT INTO prospectos (id, nombres, apellidos, email, fecha_nacimiento, rfc, telefono) VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sqlProspecto,
            prospecto.getId().toString(),
            prospecto.getNombres(),
            prospecto.getApellidos(),
            prospecto.getEmail(),
            java.sql.Date.valueOf(prospecto.getFechaNacimiento()),
            prospecto.getRfc(),
            prospecto.getTelefono()
        );

        // Insertar domicilio
        if (prospecto.getDomicilio() != null) {
            if (prospecto.getDomicilio().getId() == null) {
                prospecto.getDomicilio().setId(UUID.randomUUID());
            }
            String sqlDomicilio = "INSERT INTO domicilios (id, prospecto_id, calle, numero, colonia, ciudad, estado, pais, codigo_postal) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            jdbcTemplate.update(sqlDomicilio,
                prospecto.getDomicilio().getId().toString(),
                prospecto.getId().toString(),
                prospecto.getDomicilio().getCalle(),
                prospecto.getDomicilio().getNumero(),
                prospecto.getDomicilio().getColonia(),
                prospecto.getDomicilio().getCiudad(),
                prospecto.getDomicilio().getEstado(),
                prospecto.getDomicilio().getPais(),
                prospecto.getDomicilio().getCodigoPostal()
            );
        }

        // Insertar empleos
        if (prospecto.getListaEmpleos() != null && !prospecto.getListaEmpleos().isEmpty()) {
            String sqlEmpleo = "INSERT INTO empleos (id, prospecto_id, nombre_empresa, fecha_ingreso, fecha_salida, ingreso_mensual, giro_empresa) VALUES (?, ?, ?, ?, ?, ?, ?)";
            for (Empleo empleo : prospecto.getListaEmpleos()) {
                if (empleo.getId() == null) {
                    empleo.setId(UUID.randomUUID());
                }
                jdbcTemplate.update(sqlEmpleo,
                    empleo.getId().toString(),
                    prospecto.getId().toString(),
                    empleo.getNombreEmpresa(),
                    java.sql.Date.valueOf(empleo.getFechaIngreso()),
                    empleo.getFechaSalida() != null ? java.sql.Date.valueOf(empleo.getFechaSalida()) : null,
                    empleo.getIngresoMensual(),
                    empleo.getGiroEmpresa()
                );
            }
        }

        return prospecto;
    }

    @Override
    public Optional<Prospecto> findById(UUID id) {
        String sqlProspecto = "SELECT * FROM prospectos WHERE id = ?";
        List<Prospecto> prospectos = jdbcTemplate.query(sqlProspecto, RowMappers.prospectoRowMapper(), id.toString());
        
        if (prospectos.isEmpty()) {
            return Optional.empty();
        }

        Prospecto prospecto = prospectos.get(0);
        loadDomicilio(prospecto);
        loadEmpleos(prospecto);
        
        return Optional.of(prospecto);
    }

    @Override
    public Optional<Prospecto> findByEmail(String email) {
        String sqlProspecto = "SELECT * FROM prospectos WHERE email = ?";
        List<Prospecto> prospectos = jdbcTemplate.query(sqlProspecto, RowMappers.prospectoRowMapper(), email);
        
        if (prospectos.isEmpty()) {
            return Optional.empty();
        }

        Prospecto prospecto = prospectos.get(0);
        loadDomicilio(prospecto);
        loadEmpleos(prospecto);
        
        return Optional.of(prospecto);
    }

    @Override
    @Transactional
    public void deleteById(UUID id) {
        // Las foreign keys con ON DELETE CASCADE eliminarán domicilios y empleos automáticamente
        String sql = "DELETE FROM prospectos WHERE id = ?";
        jdbcTemplate.update(sql, id.toString());
    }

    @Override
    @Transactional
    public void deleteByEmail(String email) {
        Optional<Prospecto> prospecto = findByEmail(email);
        prospecto.ifPresent(p -> deleteById(p.getId()));
    }

    @Override
    public List<Prospecto> findByEmpresa(String nombreEmpresa) {
        String sql = "SELECT DISTINCT p.* FROM prospectos p " +
                     "INNER JOIN empleos e ON p.id = e.prospecto_id " +
                     "WHERE UPPER(e.nombre_empresa) = UPPER(?)";
        List<Prospecto> prospectos = jdbcTemplate.query(sql, RowMappers.prospectoRowMapper(), nombreEmpresa);
        
        for (Prospecto prospecto : prospectos) {
            loadDomicilio(prospecto);
            loadEmpleos(prospecto);
        }
        
        return prospectos;
    }

    @Override
    public List<Prospecto> findByIngresosMinimos(Double montoMinimo) {
        String sql = "SELECT DISTINCT p.* FROM prospectos p " +
                     "INNER JOIN empleos e ON p.id = e.prospecto_id " +
                     "WHERE e.ingreso_mensual > ?";
        List<Prospecto> prospectos = jdbcTemplate.query(sql, RowMappers.prospectoRowMapper(), montoMinimo);
        
        for (Prospecto prospecto : prospectos) {
            loadDomicilio(prospecto);
            loadEmpleos(prospecto);
        }
        
        return prospectos;
    }

    @Override
    public List<Prospecto> findByEmpleoVigente() {
        String sql = "SELECT DISTINCT p.* FROM prospectos p " +
                     "INNER JOIN empleos e ON p.id = e.prospecto_id " +
                     "WHERE e.fecha_salida IS NULL " +
                     "AND e.fecha_ingreso = (SELECT MAX(e2.fecha_ingreso) FROM empleos e2 WHERE e2.prospecto_id = p.id)";
        List<Prospecto> prospectos = jdbcTemplate.query(sql, RowMappers.prospectoRowMapper());
        
        for (Prospecto prospecto : prospectos) {
            loadDomicilio(prospecto);
            loadEmpleos(prospecto);
        }
        
        return prospectos;
    }

    @Override
    public boolean existsById(UUID id) {
        String sql = "SELECT COUNT(*) FROM prospectos WHERE id = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, id.toString());
        return count != null && count > 0;
    }

    @Override
    public boolean existsByEmail(String email) {
        String sql = "SELECT COUNT(*) FROM prospectos WHERE email = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, email);
        return count != null && count > 0;
    }

    private void loadDomicilio(Prospecto prospecto) {
        String sql = "SELECT * FROM domicilios WHERE prospecto_id = ?";
        List<Domicilio> domicilios = jdbcTemplate.query(sql, RowMappers.domicilioRowMapper(), prospecto.getId().toString());
        if (!domicilios.isEmpty()) {
            prospecto.setDomicilio(domicilios.get(0));
        }
    }

    private void loadEmpleos(Prospecto prospecto) {
        String sql = "SELECT * FROM empleos WHERE prospecto_id = ? ORDER BY fecha_ingreso DESC";
        List<Empleo> empleos = jdbcTemplate.query(sql, RowMappers.empleoRowMapper(), prospecto.getId().toString());
        prospecto.setListaEmpleos(empleos);
    }

    @Transactional
    public Prospecto update(Prospecto prospecto) {
        // Actualizar prospecto
        String sqlProspecto = "UPDATE prospectos SET nombres = ?, apellidos = ?, email = ?, fecha_nacimiento = ?, rfc = ?, telefono = ? WHERE id = ?";
        jdbcTemplate.update(sqlProspecto,
            prospecto.getNombres(),
            prospecto.getApellidos(),
            prospecto.getEmail(),
            java.sql.Date.valueOf(prospecto.getFechaNacimiento()),
            prospecto.getRfc(),
            prospecto.getTelefono(),
            prospecto.getId().toString()
        );

        // Eliminar domicilio y empleos existentes
        jdbcTemplate.update("DELETE FROM domicilios WHERE prospecto_id = ?", prospecto.getId().toString());
        jdbcTemplate.update("DELETE FROM empleos WHERE prospecto_id = ?", prospecto.getId().toString());

        // Insertar nuevo domicilio
        if (prospecto.getDomicilio() != null) {
            if (prospecto.getDomicilio().getId() == null) {
                prospecto.getDomicilio().setId(UUID.randomUUID());
            }
            String sqlDomicilio = "INSERT INTO domicilios (id, prospecto_id, calle, numero, colonia, ciudad, estado, pais, codigo_postal) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            jdbcTemplate.update(sqlDomicilio,
                prospecto.getDomicilio().getId().toString(),
                prospecto.getId().toString(),
                prospecto.getDomicilio().getCalle(),
                prospecto.getDomicilio().getNumero(),
                prospecto.getDomicilio().getColonia(),
                prospecto.getDomicilio().getCiudad(),
                prospecto.getDomicilio().getEstado(),
                prospecto.getDomicilio().getPais(),
                prospecto.getDomicilio().getCodigoPostal()
            );
        }

        // Insertar nuevos empleos
        if (prospecto.getListaEmpleos() != null && !prospecto.getListaEmpleos().isEmpty()) {
            String sqlEmpleo = "INSERT INTO empleos (id, prospecto_id, nombre_empresa, fecha_ingreso, fecha_salida, ingreso_mensual, giro_empresa) VALUES (?, ?, ?, ?, ?, ?, ?)";
            for (Empleo empleo : prospecto.getListaEmpleos()) {
                if (empleo.getId() == null) {
                    empleo.setId(UUID.randomUUID());
                }
                jdbcTemplate.update(sqlEmpleo,
                    empleo.getId().toString(),
                    prospecto.getId().toString(),
                    empleo.getNombreEmpresa(),
                    java.sql.Date.valueOf(empleo.getFechaIngreso()),
                    empleo.getFechaSalida() != null ? java.sql.Date.valueOf(empleo.getFechaSalida()) : null,
                    empleo.getIngresoMensual(),
                    empleo.getGiroEmpresa()
                );
            }
        }

        return prospecto;
    }
}

