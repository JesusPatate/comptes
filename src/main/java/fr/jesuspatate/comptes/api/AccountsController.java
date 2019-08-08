package fr.jesuspatate.comptes.api;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import fr.jesuspatate.comptes.core.Transaction;
import fr.jesuspatate.comptes.core.TransactionService;
import fr.jesuspatate.comptes.exceptions.MissingFieldException;
import org.springframework.web.bind.annotation.*;

import fr.jesuspatate.comptes.annotations.arch.Adapter;
import fr.jesuspatate.comptes.core.Account;
import fr.jesuspatate.comptes.core.AccountService;
import fr.jesuspatate.comptes.exceptions.AccountNotFoundException;
import org.springframework.web.client.HttpClientErrorException;

@Adapter
@RestController
@RequestMapping("accounts")
class AccountsController {

    private final AccountService accountService;

    private final TransactionService transactionService;

    private final AccountMapper accountMapper;

    private final TransactionMapper transactionMapper;

    AccountsController(
            final AccountService accountService,
            final TransactionService transactionService,
            final AccountMapper accountMapper,
            final TransactionMapper transactionMapper) {

        this.accountService = accountService;
        this.transactionService = transactionService;
        this.accountMapper = accountMapper;
        this.transactionMapper = transactionMapper;
    }

    @GetMapping
    public List<OutputAccountRepresentation> getAll() {
        final List<Account> listOfAccounts = this.accountService.getAll();
        final List<OutputAccountRepresentation> representations = listOfAccounts.stream()
                .map(this.accountMapper::toRepresentation)
                .collect(Collectors.toList());

        return representations;
    }

    @GetMapping("/{id}")
    public OutputAccountRepresentation getAccount(@PathVariable("id") final int id) {
        final Account account = this.accountService.get(id)
                .orElseThrow(() -> new AccountNotFoundException(id));

        final OutputAccountRepresentation representation = this.accountMapper.toRepresentation(account);
        return representation;
    }

    @GetMapping("/{id}/transactions")
    public List<TransactionRepresentation> getAccountTransactions(@PathVariable("id") final int id) {
        final List<Transaction> transactions = this.transactionService.getAccountTransactions(id);
        final List<TransactionRepresentation> representations = transactions.stream()
                .map(this.transactionMapper::toRepresentation)
                .collect(Collectors.toList());

        return representations;
    }

    @PostMapping
    public OutputAccountRepresentation createAccount(
            @RequestBody final InputAccountRepresentation representation) {

        final String name = representation.getName();
        final String type = representation.getType();
        final Double initialBalance = representation.getInitialBalance();
        final Optional<Integer> parent = representation.getParent();
        final Account account;

        if (parent.isPresent()) {
            account = this.accountService.create(name, type, BigDecimal.valueOf(initialBalance), parent.get());
        } else {
            account = this.accountService.create(name, type, BigDecimal.valueOf(initialBalance));
        }

        return this.accountMapper.toRepresentation(account);
    }

    @PutMapping
    public OutputAccountRepresentation updateAccount(
            @RequestBody final InputAccountRepresentation representation) {

        final int id = representation.getId()
                .orElseThrow(() -> new MissingFieldException("id"));

        Account account = this.accountService.get(id)
                .orElseThrow(() -> new AccountNotFoundException(id));

        final String name = representation.getName();
        final String type = representation.getType();
        final BigDecimal initialBalance = new BigDecimal(representation.getInitialBalance());
        final Optional<Integer> parent = representation.getParent();

        if (parent.isPresent()) {
            account = this.accountService.updateAccount(account, name, type, initialBalance, parent.get());
        } else {
            account = this.accountService.updateAccount(account, name, type, initialBalance);
        }

        return this.accountMapper.toRepresentation(account);
    }
}
