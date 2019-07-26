package fr.jesuspatate.comptes;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "accounts", path="accounts")
interface Accounts extends PagingAndSortingRepository<Account, Integer> {

}
