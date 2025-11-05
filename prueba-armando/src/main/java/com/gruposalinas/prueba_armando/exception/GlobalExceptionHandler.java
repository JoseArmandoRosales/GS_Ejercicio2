package com.gruposalinas.prueba_armando.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice(basePackages = "com.gruposalinas.prueba_armando.controller")
public class GlobalExceptionHandler {

    @ExceptionHandler(ProspectoNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleProspectoNotFoundException(ProspectoNotFoundException ex) {
        ErrorResponse error = new ErrorResponse(
            HttpStatus.NOT_FOUND.value(),
            ex.getMessage(),
            LocalDateTime.now()
        );
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException ex) {
        ErrorResponse error = new ErrorResponse(
            HttpStatus.BAD_REQUEST.value(),
            ex.getMessage(),
            LocalDateTime.now()
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, Object> errors = new HashMap<>();
        Map<String, String> fieldErrors = new HashMap<>();
        
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            fieldErrors.put(fieldName, errorMessage);
        });
        
        errors.put("status", HttpStatus.BAD_REQUEST.value());
        errors.put("message", "Error de validación");
        errors.put("errors", fieldErrors);
        errors.put("timestamp", LocalDateTime.now());
        
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        String parameterName = ex.getName();
        String requiredType = ex.getRequiredType() != null ? ex.getRequiredType().getSimpleName() : "desconocido";
        String providedValue = ex.getValue() != null ? ex.getValue().toString() : "null";
        
        String message;
        if (requiredType.equals("UUID")) {
            message = String.format("El parámetro '%s' debe ser un UUID válido. Valor proporcionado: '%s'", 
                parameterName, providedValue);
        } else {
            message = String.format("El parámetro '%s' debe ser de tipo %s. Valor proporcionado: '%s'", 
                parameterName, requiredType, providedValue);
        }
        
        ErrorResponse error = new ErrorResponse(
            HttpStatus.BAD_REQUEST.value(),
            message,
            LocalDateTime.now()
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorResponse> handleMissingServletRequestParameterException(MissingServletRequestParameterException ex) {
        String message = String.format("Parámetro requerido faltante: '%s' de tipo %s", 
            ex.getParameterName(), ex.getParameterType());
        
        ErrorResponse error = new ErrorResponse(
            HttpStatus.BAD_REQUEST.value(),
            message,
            LocalDateTime.now()
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        String message = "Error en el formato de los datos enviados";
        Throwable rootCause = ex.getRootCause();
        
        if (rootCause != null) {
            String rootCauseMessage = rootCause.getMessage();
            
            // Detectar errores de formato de fecha
            if (rootCause instanceof DateTimeParseException || 
                (rootCauseMessage != null && rootCauseMessage.contains("LocalDate"))) {
                
                // Extraer la fecha inválida del mensaje si es posible
                String fechaInvalida = extraerFechaInvalida(rootCauseMessage);
                if (fechaInvalida != null) {
                    message = String.format("Formato de fecha inválido: '%s'. El formato esperado es YYYY-MM-DD (ejemplo: 2025-11-05)", 
                        fechaInvalida);
                } else {
                    message = "Formato de fecha inválido. El formato esperado es YYYY-MM-DD (ejemplo: 2025-11-05)";
                }
            } 
            // Detectar otros errores de JSON
            else if (rootCauseMessage != null && rootCauseMessage.contains("JSON")) {
                message = "Error en el formato JSON. Verifica que el JSON sea válido.";
            }
            // Otros errores de deserialización
            else if (rootCauseMessage != null) {
                message = String.format("Error al procesar los datos: %s", 
                    rootCauseMessage.length() > 100 ? rootCauseMessage.substring(0, 100) + "..." : rootCauseMessage);
            }
        }
        
        ErrorResponse error = new ErrorResponse(
            HttpStatus.BAD_REQUEST.value(),
            message,
            LocalDateTime.now()
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    private String extraerFechaInvalida(String message) {
        if (message == null) {
            return null;
        }
        
        // Buscar patrones como "2030-06-32" o fechas entre comillas
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("['\"]?\\d{4}-\\d{2}-\\d{2,3}['\"]?");
        java.util.regex.Matcher matcher = pattern.matcher(message);
        if (matcher.find()) {
            String fecha = matcher.group();
            // Limpiar comillas si existen
            return fecha.replaceAll("['\"]", "");
        }
        
        return null;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
        ErrorResponse error = new ErrorResponse(
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            "Error interno del servidor: " + ex.getMessage(),
            LocalDateTime.now()
        );
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static class ErrorResponse {
        private int status;
        private String message;
        private LocalDateTime timestamp;

        public ErrorResponse(int status, String message, LocalDateTime timestamp) {
            this.status = status;
            this.message = message;
            this.timestamp = timestamp;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public LocalDateTime getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(LocalDateTime timestamp) {
            this.timestamp = timestamp;
        }
    }
}

