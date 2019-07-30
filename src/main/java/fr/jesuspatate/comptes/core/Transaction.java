package fr.jesuspatate.comptes.core;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Transaction {

    private Integer id;

    private String description;

    private LocalDate date;

    private double amount;

    private Account from;

    private Account to;

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
        this.amount = amount;
        this.from = from;
        this.to = to;
    }

    Transaction(
            final String description,
            final LocalDate date,
            final double amount,
            final Account from,
            final Account to) {

        this.description = description;
        this.date = date;
        this.amount = amount;
        this.from = from;
        this.to = to;
    }
}
