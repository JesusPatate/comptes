package fr.jesuspatate.comptes.core.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UnknownAccountTypeException extends ApiException {

    private static final String MESSAGE = "Unknown acccount type (%s)";

    public UnknownAccountTypeException(final String type) {
        super(ApiExceptionCode.UNKNOWN_ACCOUNT_TYPE, String.format(MESSAGE, type));
    }
}
