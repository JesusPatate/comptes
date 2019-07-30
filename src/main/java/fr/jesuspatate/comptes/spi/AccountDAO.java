package fr.jesuspatate.comptes.spi;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

interface AccountDAO extends PagingAndSortingRepository<DbAccount, Integer> {

    List<DbAccount> findAll();
}
