package fr.jesuspatate.comptes.core;

import java.util.List;
import java.util.Optional;

import fr.jesuspatate.comptes.exceptions.AccountNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.jesuspatate.comptes.annotations.arch.Port;

@Port
@Component
public class AccountService {

    private static final Logger LOG = LoggerFactory.getLogger(AccountService.class);

    private final Accounts accounts;

    private final Transactions transactions;

    @Autowired
    public AccountService(final Accounts accounts, final Transactions transactions) {
        this.accounts = accounts;
        this.transactions = transactions;
    }

    /**
     * Retrieves all the accounts.
     */
    public List<Account> getAll() {
        return this.accounts.getAll();
    }

    /**
     * Retrieves an account from its identifier.
     *
     * @param id Account's identifier
     */
    public Optional<Account> get(final int id) {
        return this.accounts.get(id);
    }

    public double getAccountBalance(final Account account) {
        final List<Transaction> transactions = this.transactions.findByAccount(account);
        final Account.Type type = account.getType();
        final int multiplier = (type == Account.Type.ASSET) ? -1 : 1;
        double balance = account.getInitialBalance();


        for (final Transaction transaction : transactions) {
            if (transaction.getFrom().equals(account)) {
                balance += multiplier * transaction.getAmount();
            } else {
                balance += transaction.getAmount();
            }
        }

        return balance;
    }

    /**
     * Creates a new account with no parent.
     *
     * @param name Account's name
     * @param type Account's type
     * @param initialBalance Account's initial balance
     */
    public Account create(final String name, final String type, final double initialBalance) {
        final Account.Type accountType = Account.Type.valueOf(type);
        final Account account = new Account(name, accountType, initialBalance);
        this.accounts.insert(account);
        return account;
    }

    /**
     * Creates a new account with a parent.
     * @param name Account's name
     * @param type Account's type
     * @param initialBalance Account's initial balance
     * @param parentId Parent account's identifier
     * @return Account
     */
    public Account create(final String name, final String type, final double initialBalance, final int parentId) {
        final Account.Type accountType = Account.Type.valueOf(type);
        final Account parent = this.get(parentId).orElseThrow(() -> new AccountNotFoundException(parentId));
        final Account account = new Account(name, accountType, initialBalance, parent);
        this.accounts.insert(account);
        return account;
    }
}
