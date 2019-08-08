package fr.jesuspatate.comptes.api;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.jesuspatate.comptes.annotations.arch.Adapter;
import fr.jesuspatate.comptes.core.Transaction;
import fr.jesuspatate.comptes.core.TransactionService;
import fr.jesuspatate.comptes.exceptions.TransactionNotFoundException;

@Adapter
@RestController
@RequestMapping("transactions")
class TransactionsController {

    private final TransactionService service;

    private final TransactionMapper mapper;

    TransactionsController(final TransactionService service, final TransactionMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public List<TransactionRepresentation> getAll() {
        final List<Transaction> listOfTransactions = this.service.getAll();
        final List<TransactionRepresentation> representations = listOfTransactions.stream()
                .map(this.mapper::toRepresentation)
                .collect(Collectors.toList());

        return representations;
    }

    @GetMapping("/{id}")
    public TransactionRepresentation getTransaction(@PathVariable("id") final int id) {
        final Transaction transaction = this.service.get(id)
                .orElseThrow(() -> new TransactionNotFoundException(id));

        final TransactionRepresentation representation = this.mapper.toRepresentation(transaction);
        return representation;
    }

    @PostMapping
    public TransactionRepresentation create(@RequestBody final TransactionRepresentation representation) {
        final Transaction transaction = this.service.create(
                representation.getDescription(),
                representation.getDate(),
                BigDecimal.valueOf(representation.getAmount()),
                representation.getFromAccount(),
                representation.getToAccount());

        return this.mapper.toRepresentation(transaction);
    }
}
