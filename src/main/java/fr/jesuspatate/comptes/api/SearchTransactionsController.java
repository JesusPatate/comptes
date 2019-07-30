package fr.jesuspatate.comptes.api;

import fr.jesuspatate.comptes.core.Transactions;
import fr.jesuspatate.comptes.core.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("transactions/search")
class SearchTransactionsController {
    
    private final Transactions transactions;

    private final TransactionMapper transactionMapper;

    @Autowired
    SearchTransactionsController(final Transactions transactions, final TransactionMapper transactionMapper) {
        this.transactions = transactions;
        this.transactionMapper = transactionMapper;
    }

    @GetMapping("findByDate")
    public List<TransactionRepresentation> findByDate(@RequestParam("date") final String date) {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        final LocalDate localDate = LocalDate.parse(date, formatter);
        final List<Transaction> transactions = this.transactions.findByDate(localDate);
        final List<TransactionRepresentation> representations = transactions.stream()
                .map(this.transactionMapper::toRepresentation)
                .collect(Collectors.toList());

        return representations;
    }
}
