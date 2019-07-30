package fr.jesuspatate.comptes.api;

import java.time.LocalDate;

import lombok.Getter;

@Getter
class TransactionRepresentation {

    private final Integer id;

    private final String description;

    private final LocalDate date;

    private final double amount;

    private final int fromAccount;

    private final int toAccount;

    TransactionRepresentation(
            final Integer id,
            final String description,
            final LocalDate date,
            final double amount,
            final int fromAcccount,
            final int toAccount) {

        this.id = id;
        this.description = description;
        this.date = date;
        this.amount = amount;
        this.fromAccount = fromAcccount;
        this.toAccount = toAccount;
    }
}
