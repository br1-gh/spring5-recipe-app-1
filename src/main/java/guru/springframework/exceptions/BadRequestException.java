package guru.springframework.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends NumberFormatException {

    public BadRequestException() {
        super();
    }

    public BadRequestException(String message) {
        super(message);
    }



}
