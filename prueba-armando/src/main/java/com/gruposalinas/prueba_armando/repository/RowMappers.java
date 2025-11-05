package com.gruposalinas.prueba_armando.repository;

import com.gruposalinas.prueba_armando.domain.Domicilio;
import com.gruposalinas.prueba_armando.domain.Empleo;
import com.gruposalinas.prueba_armando.domain.Prospecto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.UUID;

public class RowMappers {

    public static RowMapper<Prospecto> prospectoRowMapper() {
        return (rs, rowNum) -> {
            Prospecto prospecto = new Prospecto();
            prospecto.setId(UUID.fromString(rs.getString("id")));
            prospecto.setNombres(rs.getString("nombres"));
            prospecto.setApellidos(rs.getString("apellidos"));
            prospecto.setEmail(rs.getString("email"));
            
            java.sql.Date fechaNac = rs.getDate("fecha_nacimiento");
            if (fechaNac != null) {
                prospecto.setFechaNacimiento(fechaNac.toLocalDate());
            }
            
            prospecto.setRfc(rs.getString("rfc"));
            prospecto.setTelefono(rs.getString("telefono"));
            return prospecto;
        };
    }

    public static RowMapper<Domicilio> domicilioRowMapper() {
        return (rs, rowNum) -> {
            Domicilio domicilio = new Domicilio();
            domicilio.setId(UUID.fromString(rs.getString("id")));
            domicilio.setCalle(rs.getString("calle"));
            domicilio.setNumero(rs.getString("numero"));
            domicilio.setColonia(rs.getString("colonia"));
            domicilio.setCiudad(rs.getString("ciudad"));
            domicilio.setEstado(rs.getString("estado"));
            domicilio.setPais(rs.getString("pais"));
            domicilio.setCodigoPostal(rs.getString("codigo_postal"));
            return domicilio;
        };
    }

    public static RowMapper<Empleo> empleoRowMapper() {
        return (rs, rowNum) -> {
            Empleo empleo = new Empleo();
            empleo.setId(UUID.fromString(rs.getString("id")));
            empleo.setNombreEmpresa(rs.getString("nombre_empresa"));
            
            java.sql.Date fechaIngreso = rs.getDate("fecha_ingreso");
            if (fechaIngreso != null) {
                empleo.setFechaIngreso(fechaIngreso.toLocalDate());
            }
            
            java.sql.Date fechaSalida = rs.getDate("fecha_salida");
            if (fechaSalida != null) {
                empleo.setFechaSalida(fechaSalida.toLocalDate());
            }
            
            empleo.setIngresoMensual(rs.getDouble("ingreso_mensual"));
            empleo.setGiroEmpresa(rs.getString("giro_empresa"));
            return empleo;
        };
    }
}

