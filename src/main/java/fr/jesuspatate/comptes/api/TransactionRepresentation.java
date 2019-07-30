package fr.jesuspatate.comptes.api;

import lombok.Getter;

import java.time.LocalDate;

@Getter
class TransactionRepresentation {

    private final Integer id;

    private final String description;

    private final LocalDate date;

    private final double amount;

    private final int fromAcccount;

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
        this.fromAcccount = fromAcccount;
        this.toAccount = toAccount;
    }
}
