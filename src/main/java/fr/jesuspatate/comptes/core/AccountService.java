package fr.jesuspatate.comptes.core;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.jesuspatate.comptes.annotations.arch.Port;

@Port
@Component
public class AccountService {

    private final Accounts accounts;

    @Autowired
    public AccountService(final Accounts accounts) {
        this.accounts = accounts;
    }

    public List<Account> getAll() {
        return this.accounts.getAll();
    }

    public Optional<Account> get(final int id) {
        return this.accounts.get(id);
    }

    public Account create(final String name, final String type) {
        final Account.Type accountType = Account.Type.valueOf(type);
        final Account account = new Account(name, accountType);
        this.accounts.insert(account);
        return account;
    }
}
