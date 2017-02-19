package com.tanya.loan;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "loan", path = "loan")
public interface LoanRepository extends CrudRepository<Loan, Long> {
}
