package fr.jesuspatate.comptes;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "transactions", path="transactions")
interface Transactions extends PagingAndSortingRepository<Transaction, Integer> {

}
