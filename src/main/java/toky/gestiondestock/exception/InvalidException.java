package toky.gestiondestock.exception;

import lombok.Getter;

import java.util.List;

public class InvalidException extends RuntimeException {
    @Getter
    private ErrorCodes errorCodes;
    @Getter
    private List<String> errors;

    public InvalidException(String message) {
        super(message);
    }

    public InvalidException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidException(String message, Throwable cause, ErrorCodes codes) {
        super(message, cause);
        this.errorCodes = codes;
    }

    public InvalidException(String message, ErrorCodes codes) {
        super(message);
        this.errorCodes = codes;
    }

    public InvalidException(String message, ErrorCodes codes, List<String> errors) {
        super(message);
        this.errorCodes = codes;
        this.errors = errors;
    }
}
