package fr.jesuspatate.comptes.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
class OutputAccountRepresentation {

    private final Integer id;

    private final String name;

    private final String type;

    private final double balance;

    private final Integer parent;

    OutputAccountRepresentation(
            final Integer id,
            final String name,
            final String type,
            final double balance,
            final Integer parent) {

        this.id = id;
        this.name = name;
        this.type = type;
        this.balance = balance;
        this.parent = parent;
    }
}
