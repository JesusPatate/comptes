package fr.jesuspatate.comptes.api;

import fr.jesuspatate.comptes.core.Account;
import org.springframework.stereotype.Component;

@Component
class AccountMapper {

    AccountRepresentation toRepresentation(final Account account) {
        return new AccountRepresentation(
                account.getId(),
                account.getName(),
                String.valueOf(account.getType()));
    }
}
