package fr.jesuspatate.comptes.api;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;
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
            @JsonProperty("id") final Integer id,
            @JsonProperty("description") final String description,
            @JsonProperty("date") final LocalDate date,
            @JsonProperty("amount") final double amount,
            @JsonProperty("fromAccount") final int fromAcccount,
            @JsonProperty("toAccount") final int toAccount) {

        this.id = id;
        this.description = description;
        this.date = date;
        this.amount = amount;
        this.fromAccount = fromAcccount;
        this.toAccount = toAccount;
    }
}
