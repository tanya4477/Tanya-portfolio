package com.tanya.repository;

import java.util.ArrayList;
import java.util.List;

import com.tanya.model.Customer;

public class HibernateCustomerRepositoryImpl implements CustomerRepository {

	/* (non-Javadoc)
	 * @see com.tanya.repository.CustomerRepository#findAll()
	 */
	@Override
	public List<Customer> findAll() {
		List<Customer> customers = new ArrayList<>();
		
		Customer customer = new Customer();
		
		customer.setFirstname("Tanya");
		customer.setLastname("Mazumdar");
		
		customers.add(customer);
		
		return customers;
	}
}
