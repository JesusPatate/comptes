package fr.jesuspatate.comptes.core.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class MissingFieldException extends ApiException {

    private static final String MESSAGE = "%s field must be supplied";

    public MissingFieldException(final String field) {
        super(ApiExceptionCode.MISSING_FIELD, String.format(MESSAGE, field));
    }
}
