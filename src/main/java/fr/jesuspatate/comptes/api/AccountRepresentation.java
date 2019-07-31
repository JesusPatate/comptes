package fr.jesuspatate.comptes.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
class AccountRepresentation {

    private final Integer id;

    private final String name;

    private final String type;

    AccountRepresentation(
            @JsonProperty("id") final Integer id,
            @JsonProperty("name") final String name,
            @JsonProperty("type") final String type) {

        this.id = id;
        this.name = name;
        this.type = type;
    }
}
