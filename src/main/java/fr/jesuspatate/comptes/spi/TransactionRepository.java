package fr.jesuspatate.comptes.spi;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import fr.jesuspatate.comptes.core.Account;
import org.springframework.stereotype.Component;

import fr.jesuspatate.comptes.core.Transaction;
import fr.jesuspatate.comptes.core.Transactions;

@Component
class TransactionRepository implements Transactions {

    private final TransactionDAO transactionDAO;

    private final AccountDAO accountDAO;

    private final TransactionMapper transactionMapper;

    TransactionRepository(
            final TransactionDAO transactionDAO,
            final AccountDAO accountDAO,
            final TransactionMapper transactionMapper) {

        this.transactionDAO = transactionDAO;
        this.accountDAO = accountDAO;
        this.transactionMapper = transactionMapper;
    }

    @Override
    public List<Transaction> getAll() {
        return this.transactionDAO.findAll()
                .stream()
                .map(this.transactionMapper::fromDbModel)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Transaction> get(final int id) {
        return this.transactionDAO.findById(id)
                .map(this.transactionMapper::fromDbModel);
    }

    @Override
    public List<Transaction> findByDate(final LocalDate date) {
        return this.transactionDAO.findByDate(date)
                .stream()
                .map(this.transactionMapper::fromDbModel)
                .collect(Collectors.toList());
    }

    @Override
    public void create(final Transaction transaction) {
        final DbTransaction dbTransaction = this.transactionMapper.toDbModel(transaction);
        this.transactionDAO.save(dbTransaction);
        transaction.setId(dbTransaction.getId());
    }

    @Override
    public List<Transaction> findByAccount(final Account account) {
        final DbAccount dbAccount = this.accountDAO.findById(account.getId())
                .orElseThrow(() -> new RuntimeException("Could not find account " + account.getName() +
                        " (" + account.getId() + ")"));

        final List<DbTransaction> dbTransactions = this.transactionDAO.findByAccount(dbAccount);
        final List<Transaction> transactions = dbTransactions.stream()
                .map(this.transactionMapper::fromDbModel)
                .collect(Collectors.toList());

        return transactions;
    }

    @Override
    public void delete(final Transaction transaction) {
        final DbTransaction dbTransaction = this.transactionMapper.toDbModel(transaction);
        this.transactionDAO.delete(dbTransaction);
    }
}
