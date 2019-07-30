package fr.jesuspatate.comptes.api;

import fr.jesuspatate.comptes.core.Account;
import fr.jesuspatate.comptes.core.Accounts;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("accounts")
class AccountsController {

    private final Accounts accounts;

    AccountsController(final Accounts accounts) {
        this.accounts = accounts;
    }

    @GetMapping
    public List<AccountRepresentation> getAll() {
        final List<Account> listOfAccounts = this.accounts.getAll();
        final List<AccountRepresentation> representations = listOfAccounts.stream()
                .map(this::toRepresentation)
                .collect(Collectors.toList());

        return representations;
    }

    @GetMapping("/{id}")
    public AccountRepresentation getAccount(@PathVariable("id") final int id) {
        final Account account = this.accounts.get(id)
                .orElseThrow(() -> new AccountNotFoundException(id));

        final AccountRepresentation representation = this.toRepresentation(account);
        return representation;
    }

    private AccountRepresentation toRepresentation(final Account account) {
        return new AccountRepresentation(account.getId(), account.getName(), String.valueOf(account.getType()));
    }
}
