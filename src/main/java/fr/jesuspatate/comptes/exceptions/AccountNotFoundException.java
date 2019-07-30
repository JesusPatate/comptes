package fr.jesuspatate.comptes.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.fasterxml.jackson.annotation.JsonIgnore;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class AccountNotFoundException extends ApiException {

    private static final String MESSAGE = "Account %d not found";

    @JsonIgnore
    private final int id;

    public AccountNotFoundException(final int id) {
        super(ApiExceptionCode.ACCOUNT_NOT_FOUND, String.format(MESSAGE, id));
        this.id = id;
    }
}
