package fr.jesuspatate.comptes.spi;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

interface TransactionDAO extends PagingAndSortingRepository<DbTransaction, Integer> {

    List<DbTransaction> findAll();

    List<DbTransaction> findByDate(LocalDate date);

    @Query("SELECT t FROM DbTransaction t WHERE t.from = ?1 OR t.to = ?1 ORDER BY t.date")
    List<DbTransaction> findByAccount(DbAccount account);
}
