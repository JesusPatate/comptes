package fr.jesuspatate.comptes.api;

import lombok.Getter;

@Getter
class AccountRepresentation {

    private final Integer id;

    private final String name;

    private final String type;

    AccountRepresentation(final Integer id, final String name, final String type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }
}
