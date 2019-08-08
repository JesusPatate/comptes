package fr.jesuspatate.comptes.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;

import java.util.Optional;

@Getter(AccessLevel.PACKAGE)
class InputAccountRepresentation {

    private final Integer id;

    private final String name;

    private final String type;

    private final Double initialBalance;

    private final Integer parent;

    InputAccountRepresentation(
            @JsonProperty("id") final Integer id,
            @JsonProperty("name") final String name,
            @JsonProperty("type") final String type,
            @JsonProperty("initialBalance") final Double initialBalance,
            @JsonProperty("parent") final Integer parent) {
        this.id = id;

        this.name = name;
        this.type = type;
        this.initialBalance = (initialBalance != null) ? initialBalance : 0.0;
        this.parent = parent;
    }

    Optional<Integer> getId() {
        return Optional.ofNullable(this.id);
    }

    Optional<Integer> getParent() {
        return Optional.ofNullable(this.parent);
    }
}
