package com.gruposalinas.prueba_armando.exception;

public class ProspectoNotFoundException extends RuntimeException {
    
    public ProspectoNotFoundException(String message) {
        super(message);
    }
    
    public ProspectoNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}

