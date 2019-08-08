package fr.jesuspatate.comptes.core;

import java.util.List;
import java.util.Optional;

import fr.jesuspatate.comptes.annotations.arch.Port;

@Port
public interface Accounts {

    List<Account> getAll();

    Optional<Account> get(int id);

    void insert(Account account);

    void update(Account account);
}
