package fr.jesuspatate.comptes.api;

import java.util.List;
import java.util.stream.Collectors;

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

    private final AccountService service;

    AccountsController(final AccountService service) {
        this.service = service;
    }

    @GetMapping
    public List<AccountRepresentation> getAll() {
        final List<Account> listOfAccounts = this.service.getAll();
        final List<AccountRepresentation> representations = listOfAccounts.stream()
                .map(this::toRepresentation)
                .collect(Collectors.toList());

        return representations;
    }

    @GetMapping("/{id}")
    public AccountRepresentation getAccount(@PathVariable("id") final int id) {
        final Account account = this.service.get(id)
                .orElseThrow(() -> new AccountNotFoundException(id));

        final AccountRepresentation representation = this.toRepresentation(account);
        return representation;
    }

    @PostMapping
    public AccountRepresentation createAccount(
            @RequestBody final AccountRepresentation representation) {

        final String name = representation.getName();
        final String type = representation.getType();
        final Account account = this.service.create(name, type);
        return this.toRepresentation(account);
    }

    private AccountRepresentation toRepresentation(final Account account) {
        return new AccountRepresentation(
                account.getId(),
                account.getName(),
                String.valueOf(account.getType()));
    }
}
