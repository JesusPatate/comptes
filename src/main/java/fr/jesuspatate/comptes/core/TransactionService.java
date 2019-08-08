package fr.jesuspatate.comptes.core;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.jesuspatate.comptes.exceptions.AccountNotFoundException;

@Component
public class TransactionService {

    private final Accounts accounts;

    private final Transactions transactions;

    @Autowired
    public TransactionService(final Accounts accounts, final Transactions transactions) {
        this.accounts = accounts;
        this.transactions = transactions;
    }

    public List<Transaction> getAll() {
        return this.transactions.getAll();
    }

    public Optional<Transaction> get(final int id) {
        return this.transactions.get(id);
    }

    public List<Transaction> findByDate(final LocalDate date) {
        return this.transactions.findByDate(date);
    }

    public Transaction create(
            final String description,
            final LocalDate date,
            final BigDecimal amount,
            final int fromAccountId,
            final int toAccountId) {

        final Account fromAccount = this.accounts.get(fromAccountId)
                .orElseThrow(() -> new AccountNotFoundException(fromAccountId));

        final Account toAccount = this.accounts.get(toAccountId)
                .orElseThrow(() -> new AccountNotFoundException(toAccountId));

        final Transaction transaction = new Transaction(
                description,
                date,
                amount,
                fromAccount,
                toAccount);

        this.transactions.create(transaction);
        return transaction;
    }

    public List<Transaction> getAccountTransactions(final int id) {
        final Account account = this.accounts.get(id)
                .orElseThrow(() -> new AccountNotFoundException(id));

        return this.transactions.findByAccount(account);
    }
}
