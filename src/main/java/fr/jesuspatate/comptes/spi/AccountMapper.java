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
        return dbAccount;
    }

    Account fromDbModel(final DbAccount dbAccount) {
        final Account.Type type = this.convertType(dbAccount.getType());
        return new Account(dbAccount.getId(), dbAccount.getName(), type);
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