package fr.jesuspatate.comptes.core;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import fr.jesuspatate.comptes.annotations.arch.Port;

@Port
public interface Transactions {

    List<Transaction> getAll();

    Optional<Transaction> get(int id);

    List<Transaction> findByDate(LocalDate date);

    void create(Transaction transaction);
}
