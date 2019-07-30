package fr.jesuspatate.comptes.core;

import java.util.List;
import java.util.Optional;

public interface Accounts {

    List<Account> getAll();

    Optional<Account> get(int id);
}
