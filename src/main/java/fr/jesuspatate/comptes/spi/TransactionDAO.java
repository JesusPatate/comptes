package fr.jesuspatate.comptes.spi;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

interface TransactionDAO extends PagingAndSortingRepository<DbTransaction, Integer> {

    List<DbTransaction> findAll();

    List<DbTransaction> findByDate(LocalDate date);

    List<DbTransaction> findByFrom(DbAccount account);

    List<DbTransaction> findByTo(DbAccount account);
}
