package fr.jesuspatate.comptes.api;

import fr.jesuspatate.comptes.core.Account;
import fr.jesuspatate.comptes.core.AccountService;
import org.springframework.stereotype.Component;

@Component
class AccountMapper {

    private final AccountService service;

    AccountMapper(final AccountService service) {
        this.service = service;
    }

    OutputAccountRepresentation toRepresentation(final Account account) {
        final Integer parentId = account.getParent()
                .map(Account::getId)
                .orElse(null);

        final double balance = this.service.getAccountBalance(account);

        return new OutputAccountRepresentation(
                account.getId(),
                account.getName(),
                String.valueOf(account.getType()),
                balance,
                parentId);
    }
}
