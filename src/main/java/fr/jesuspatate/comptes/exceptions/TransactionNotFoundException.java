package fr.jesuspatate.comptes.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.fasterxml.jackson.annotation.JsonIgnore;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class TransactionNotFoundException extends ApiException {

    private static final String MESSAGE = "Transaction %d not found";

    @JsonIgnore
    private final int id;

    public TransactionNotFoundException(final int id) {
        super(ApiExceptionCode.TRANSACTION_NOT_FOUND, String.format(MESSAGE, id));
        this.id = id;
    }
}
