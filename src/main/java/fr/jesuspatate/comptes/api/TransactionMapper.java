package fr.jesuspatate.comptes.api;

import fr.jesuspatate.comptes.core.Transaction;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
class TransactionMapper {

    TransactionRepresentation toRepresentation(final Transaction transaction) {
        final BigDecimal amount = transaction.getAmount();

        return new TransactionRepresentation(
                transaction.getId(),
                transaction.getDescription(),
                transaction.getDate(),
                amount.doubleValue(),
                transaction.getFrom().getId(),
                transaction.getTo().getId());
    }
}