package fr.jesuspatate.comptes.core;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Transaction {

    private Integer id;

    private String description;

    private LocalDate date;

    private BigDecimal amount;

    private Account from;

    private Account to;

    /**
     * Constructor intended only for Hibernate.
     *
     * @param id Identifier of the transaction
     * @param description Short description of the transaction
     * @param date Date of transaction
     * @param amount Amount of the transaction
     * @param from Source account of the transaction
     * @param to Destination account of the transaction
     */
    public Transaction(
            final int id,
            final String description,
            final LocalDate date,
            final double amount,
            final Account from,
            final Account to) {

        this.id = id;
        this.description = description;
        this.date = date;
        this.amount = BigDecimal.valueOf(amount);
        this.from = from;
        this.to = to;
    }

    Transaction(
            final String description,
            final LocalDate date,
            final BigDecimal amount,
            final Account from,
            final Account to) {

        this.description = description;
        this.date = date;
        this.amount = amount;
        this.from = from;
        this.to = to;
    }
}
