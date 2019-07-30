package fr.jesuspatate.comptes.api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
class TransactionNotFoundException extends ApiException {

    private static final String MESSAGE = "Transaction %d not found";

    @JsonIgnore
    private final int id;

    TransactionNotFoundException(final int id) {
        super(ApiExceptionCode.TRANSACTION_NOT_FOUND, String.format(MESSAGE, id));
        this.id = id;
    }
}
