package fr.jesuspatate.comptes.core;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Account {

    public enum Type {
        ASSET, EXPENSE, INCOME
    }

    private Integer id;

    private String name;

    private Type type;

    private Account parent;

    public Account(final int id, final String name, final Type type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    Account(final String name, final Type type) {
        this.name = name;
        this.type = type;
    }
}
