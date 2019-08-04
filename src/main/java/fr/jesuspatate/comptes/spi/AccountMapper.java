package fr.jesuspatate.comptes.spi;

import org.springframework.stereotype.Component;

import fr.jesuspatate.comptes.core.Account;

@Component("DbAccountMapper")
public class AccountMapper {
    public AccountMapper() {
    }

    DbAccount toDbModel(final Account account) {
        final DbAccount dbAccount = new DbAccount();
        dbAccount.setName(account.getName());
        dbAccount.setType(this.convertType(account.getType()));
        dbAccount.setInitialBalance(account.getInitialBalance());

        account.getParent()
                .map(this::toDbModel)
                .ifPresent(dbAccount::setParent);

        return dbAccount;
    }

    Account fromDbModel(final DbAccount dbAccount) {
        final Account.Type type = this.convertType(dbAccount.getType());
        final DbAccount dbParent = dbAccount.getParent();
        final Account account;

        if (dbParent != null) {
            final Account parent = this.fromDbModel(dbParent);
            account = new Account(dbAccount.getId(), dbAccount.getName(), type, dbAccount.getInitialBalance(), parent);
        } else {
            account = new Account(dbAccount.getId(), dbAccount.getName(), type, dbAccount.getInitialBalance());
        }

        return account;
    }

    private DbAccount.Type convertType(final Account.Type type) {
        final String str = String.valueOf(type);
        return DbAccount.Type.valueOf(str);
    }

    private Account.Type convertType(final DbAccount.Type dbType) {
        final String str = String.valueOf(dbType);
        return Account.Type.valueOf(str);
    }
}