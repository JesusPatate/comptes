package fr.jesuspatate.comptes.api;

import fr.jesuspatate.comptes.core.Transaction;
import org.springframework.stereotype.Component;

@Component
class TransactionMapper {

    TransactionRepresentation toRepresentation(final Transaction transaction) {
        return new TransactionRepresentation(
                transaction.getId(),
                transaction.getDescription(),
                transaction.getDate(),
                transaction.getAmount(),
                transaction.getFrom().getId(),
                transaction.getTo().getId());
    }
}