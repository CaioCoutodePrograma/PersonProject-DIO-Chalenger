package one.digitalinnovation.personapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PhoneNotFoundException extends Exception {
    public PhoneNotFoundException(Long id) {
        super("Phone not found with ID " + id);
    }
}
