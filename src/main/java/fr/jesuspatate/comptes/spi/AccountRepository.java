package fr.jesuspatate.comptes.spi;

import fr.jesuspatate.comptes.core.Account;
import fr.jesuspatate.comptes.core.Accounts;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
class AccountRepository implements Accounts {

    private final AccountDAO dao;

    AccountRepository(final AccountDAO dao) {
        this.dao = dao;
    }

    @Override
    public List<Account> getAll() {
        return this.dao.findAll();
    }

    @Override
    public Optional<Account> get(final int id) {
        return this.dao.findById(id);
    }
}
