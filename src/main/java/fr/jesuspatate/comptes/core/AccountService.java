package fr.jesuspatate.comptes.core;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import fr.jesuspatate.comptes.core.exceptions.AccountNotFoundException;
import fr.jesuspatate.comptes.core.exceptions.UnknownAccountTypeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.jesuspatate.comptes.annotations.arch.Port;

@Port
@Component
public class AccountService {

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

    /**
     * Computes thr balance of an account.
     *
     * @param account The account whose balance must be computed
     * @return The balance of the account
     */
    public BigDecimal getAccountBalance(final Account account) {
        final List<Transaction> transactions = this.transactions.findByAccount(account);
        final Account.Type type = account.getType();
        final BigDecimal multiplier = (type == Account.Type.ASSET) ? new BigDecimal(-1) : BigDecimal.ONE;
        BigDecimal balance = account.getInitialBalance();


        for (final Transaction transaction : transactions) {
            if (transaction.getFrom().equals(account)) {
                balance = balance.add(multiplier.multiply(transaction.getAmount()));
            } else {
                balance = balance.add(transaction.getAmount());
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
     * @return The new account
     */
    public Account create(final String name, final String type, final BigDecimal initialBalance) {
        final Account.Type accountType = Account.Type.valueOf(type);
        final Account account = new Account(name, accountType, initialBalance);
        this.accounts.insert(account);
        return account;
    }

    /**
     * Creates a new account with a parent.
     *
     * @param name Account's name
     * @param type Account's type
     * @param initialBalance Account's initial balance
     * @param parentId Parent account's identifier
     * @return The new account
     */
    public Account create(final String name, final String type, final BigDecimal initialBalance, final int parentId) {
        final Account.Type accountType = Account.Type.valueOf(type);
        final Account parent = this.get(parentId).orElseThrow(() -> new AccountNotFoundException(parentId));
        final Account account = new Account(name, accountType, initialBalance, parent);
        this.accounts.insert(account);
        return account;
    }

    /**
     * Updates an existing account.
     *
     * @param account The updated account
     * @return The updated account
     */
    public Account updateAccount(
            final Account account,
            final String name,
            final String type,
            final BigDecimal initialBalance) {

        account.setName(name);
        account.setType(this.getAccountType(type));
        account.setInitialBalance(initialBalance);
        this.accounts.update(account);
        return account;
    }

    /**
     * Updates an existing account.
     *
     * @param account The updated account
     * @return The updated account
     */
    public Account updateAccount(
            final Account account,
            final String name,
            final String type,
            final BigDecimal initialBalance,
            final int parentId) {

        final Account parent = this.accounts.get(parentId)
                .orElseThrow(() -> new AccountNotFoundException(parentId));

        account.setName(name);
        account.setType(this.getAccountType(type));
        account.setInitialBalance(initialBalance);
        account.setParent(parent);
        this.accounts.update(account);
        return account;
    }

    private Account.Type getAccountType(final String string) {
        final Account.Type type;

        try {
            type = Account.Type.valueOf(string);
        } catch (IllegalArgumentException e) {
            throw new UnknownAccountTypeException(string);
        }

        return type;
    }
}
