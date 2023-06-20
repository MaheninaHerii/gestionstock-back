package toky.gestiondestock.exception;

import lombok.Getter;

public class NotFoundException extends RuntimeException {
    @Getter
    private ErrorCodes errorCodes;

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundException(String message, Throwable cause, ErrorCodes codes) {
        super(message, cause);
        this.errorCodes = codes;
    }

    public NotFoundException(String message, ErrorCodes codes) {
        super(message);
        this.errorCodes = codes;
    }
}
