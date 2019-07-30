package fr.jesuspatate.comptes.api;

import fr.jesuspatate.comptes.core.Transaction;
import fr.jesuspatate.comptes.core.Transactions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("transactions")
class TransactionsController {

    private final Transactions transactions;

    private final TransactionMapper transactionMapper;

    TransactionsController(final Transactions transactions, final TransactionMapper transactionMapper) {
        this.transactions = transactions;
        this.transactionMapper = transactionMapper;
    }

    @GetMapping
    public List<TransactionRepresentation> getAll() {
        final List<Transaction> listOfTransactions = this.transactions.getAll();
        final List<TransactionRepresentation> representations = listOfTransactions.stream()
                .map(this::toRepresentation)
                .collect(Collectors.toList());

        return representations;
    }

    @GetMapping("/{id}")
    public TransactionRepresentation getTransaction(@PathVariable("id") final int id) {
        final Transaction transaction = this.transactions.get(id)
                .orElseThrow(() -> new TransactionNotFoundException(id));

        final TransactionRepresentation representation = this.transactionMapper.toRepresentation(transaction);
        return representation;
    }

    private TransactionRepresentation toRepresentation(final Transaction transaction) {
        return this.transactionMapper.toRepresentation(transaction);
    }
}
