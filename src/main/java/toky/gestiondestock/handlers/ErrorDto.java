package toky.gestiondestock.handlers;

import lombok.*;
import toky.gestiondestock.exception.ErrorCodes;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorDto {

    private Integer httpCode;
    private ErrorCodes errorCodes;
    private String message;
    private List<String> details = new ArrayList<>();
}
