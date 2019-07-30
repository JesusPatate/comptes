package fr.jesuspatate.comptes.exceptions;

import java.util.HashMap;
import java.util.Map;

abstract class ApiException extends RuntimeException {

    protected final ApiExceptionCode code;

    protected final Map<String, Object> parameters = new HashMap<>();

    ApiException(final ApiExceptionCode code, final String message) {
        super(message);
        this.code = code;
    }

    public ApiExceptionCode getCode() {
        return this.code;
    }
}
