package fr.jesuspatate.comptes.api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
class AccountNotFoundException extends ApiException {

    private static final String MESSAGE = "Account %d not found";

    @JsonIgnore
    private final int id;

    AccountNotFoundException(final int id) {
        super(ApiExceptionCode.ACCOUNT_NOT_FOUND, String.format(MESSAGE, id));
        this.id = id;
    }
}
