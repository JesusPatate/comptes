package fr.jesuspatate.comptes.core;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface Transactions {

    List<Transaction> getAll();

    Optional<Transaction> get(int id);

    List<Transaction> findByDate(LocalDate date);
}
