package fr.jesuspatate.comptes.spi;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import fr.jesuspatate.comptes.core.Account;
import fr.jesuspatate.comptes.core.Accounts;

@Component
class AccountRepository implements Accounts {

    private final AccountDAO dao;

    private final AccountMapper accountMapper;

    AccountRepository(final AccountDAO dao, final AccountMapper accountMapper) {
        this.dao = dao;
        this.accountMapper = accountMapper;
    }

    @Override
    public List<Account> getAll() {
        return this.dao.findAll()
                .stream()
                .map(this.accountMapper::fromDbModel)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Account> get(final int id) {
        return this.dao.findById(id)
                .map(this.accountMapper::fromDbModel);
    }

    @Override
    public void insert(final Account account) {
        final DbAccount dbAccount = this.accountMapper.toDbModel(account);
        this.dao.save(dbAccount);
        account.setId(dbAccount.getId());
    }

    @Override
    public void update(final Account account) {
        final DbAccount dbAccount = this.accountMapper.toDbModel(account);
        this.dao.save(dbAccount);
    }
}
