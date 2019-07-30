package fr.jesuspatate.comptes.spi;

import fr.jesuspatate.comptes.core.Transaction;
import fr.jesuspatate.comptes.core.Transactions;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Component
class TransactionRepository implements Transactions {

    private final TransactionDAO dao;

    TransactionRepository(final TransactionDAO dao) {
        this.dao = dao;
    }

    @Override
    public List<Transaction> getAll() {
        return this.dao.findAll();
    }

    @Override
    public Optional<Transaction> get(final int id) {
        return this.dao.findById(id);
    }

    @Override
    public List<Transaction> findByDate(final LocalDate date) {
        return this.dao.findByDate(date);
    }
}
