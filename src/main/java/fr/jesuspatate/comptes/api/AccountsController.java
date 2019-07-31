package fr.jesuspatate.comptes.api;

import java.util.List;
import java.util.stream.Collectors;

import fr.jesuspatate.comptes.core.Transaction;
import fr.jesuspatate.comptes.core.TransactionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.jesuspatate.comptes.annotations.arch.Adapter;
import fr.jesuspatate.comptes.core.Account;
import fr.jesuspatate.comptes.core.AccountService;
import fr.jesuspatate.comptes.exceptions.AccountNotFoundException;

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
    public List<AccountRepresentation> getAll() {
        final List<Account> listOfAccounts = this.accountService.getAll();
        final List<AccountRepresentation> representations = listOfAccounts.stream()
                .map(this.accountMapper::toRepresentation)
                .collect(Collectors.toList());

        return representations;
    }

    @GetMapping("/{id}")
    public AccountRepresentation getAccount(@PathVariable("id") final int id) {
        final Account account = this.accountService.get(id)
                .orElseThrow(() -> new AccountNotFoundException(id));

        final AccountRepresentation representation = this.accountMapper.toRepresentation(account);
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
    public AccountRepresentation createAccount(
            @RequestBody final AccountRepresentation representation) {

        final String name = representation.getName();
        final String type = representation.getType();
        final Account account = this.accountService.create(name, type);
        return this.accountMapper.toRepresentation(account);
    }
}
