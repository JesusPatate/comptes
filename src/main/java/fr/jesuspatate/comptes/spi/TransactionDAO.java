package fr.jesuspatate.comptes.spi;

import fr.jesuspatate.comptes.core.Transaction;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.time.LocalDate;
import java.util.List;

interface TransactionDAO extends PagingAndSortingRepository<Transaction, Integer> {

    List<Transaction> findAll();

    List<Transaction> findByDate(LocalDate date);
}
