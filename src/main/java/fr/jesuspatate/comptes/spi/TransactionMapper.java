package fr.jesuspatate.comptes.spi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.jesuspatate.comptes.core.Account;
import fr.jesuspatate.comptes.core.Transaction;

@Component("DbTransactionMapper")
class TransactionMapper {

    private final AccountDAO accountDAO;

    private final AccountMapper accountMapper;

    @Autowired
    TransactionMapper(final AccountDAO accountDAO, final AccountMapper accountMapper) {
        this.accountDAO = accountDAO;
        this.accountMapper = accountMapper;
    }

    DbTransaction toDbModel(final Transaction transaction) {
        final DbTransaction dbTransaction = new DbTransaction();
        dbTransaction.setDescription(transaction.getDescription());
        dbTransaction.setDate(transaction.getDate());
        dbTransaction.setAmount(transaction.getAmount());

        final int fromAccountId = transaction.getFrom().getId();
        final DbAccount fromAccount = this.accountDAO.findById(fromAccountId)
                .orElseThrow(() -> new RuntimeException(
                        "Account " + fromAccountId + " could not be found in database"));


        final int toAccountId = transaction.getTo().getId();
        final DbAccount toAccount = this.accountDAO.findById(toAccountId)
                .orElseThrow(() -> new RuntimeException(
                        "Account " + toAccountId + " could not be found in database"));

        dbTransaction.setFrom(fromAccount);
        dbTransaction.setTo(toAccount);
        return dbTransaction;
    }

    Transaction fromDbModel(final DbTransaction dbTransaction) {
        final Account fromAccount = this.accountMapper.fromDbModel(dbTransaction.getFrom());
        final Account toAccount = this.accountMapper.fromDbModel(dbTransaction.getTo());
        return new Transaction(
                dbTransaction.getId(),
                dbTransaction.getDescription(),
                dbTransaction.getDate(),
                dbTransaction.getAmount(),
                fromAccount,
                toAccount);
    }
}