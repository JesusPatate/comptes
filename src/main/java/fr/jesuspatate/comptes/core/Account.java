package fr.jesuspatate.comptes.core;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;
import java.util.Optional;

@Getter
@Setter
public class Account {

    public enum Type {
        ASSET, EXPENSE, INCOME
    }

    private Integer id;

    private String name;

    private Type type;

    private double initialBalance;

    private Account parent;

    /**
     * Constructor intended for Hibernate.
     *
     * @param id Account's unique identifier
     * @param name Account's name
     * @param type Account's type
     * @param initialBalance Account's initial balance
     */
    public Account(
            final int id,
            final String name,
            final Type type,
            final double initialBalance) {

        this.id = id;
        this.name = name;
        this.type = type;
        this.initialBalance = initialBalance;
    }

    /**
     * Constructor intended for Hibernate.
     *
     * @param id Account's unique identifier
     * @param name Account's name
     * @param type Account's type
     * @param initialBalance Account's initial balance
     * @param parent Account's parent
     */
    public Account(
            final int id,
            final String name,
            final Type type,
            final double initialBalance,
            final Account parent) {

        Objects.requireNonNull(parent);

        this.id = id;
        this.name = name;
        this.type = type;
        this.initialBalance = initialBalance;
        this.parent = parent;
    }

    Account(final String name, final Type type, final double initialBalance) {
        this.name = name;
        this.type = type;
        this.initialBalance = initialBalance;
    }

    Account(final String name, final Type type, final double initialBalance, final Account parent) {
        Objects.requireNonNull(parent);

        this.name = name;
        this.type = type;
        this.initialBalance = initialBalance;
        this.parent = parent;
    }

    public Optional<Account> getParent() {
        return Optional.ofNullable(this.parent);
    }

    @Override
    public boolean equals(final Object object) {
        if (this == object) {
            return true;
        }

        if (object == null || this.getClass() != object.getClass()) {
            return false;
        }

        final Account other = (Account) object;

        return this.id.equals(other.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }
}
