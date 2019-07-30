package fr.jesuspatate.comptes.spi;

import fr.jesuspatate.comptes.core.Account;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

interface AccountDAO extends PagingAndSortingRepository<Account, Integer> {

    List<Account> findAll();
}
