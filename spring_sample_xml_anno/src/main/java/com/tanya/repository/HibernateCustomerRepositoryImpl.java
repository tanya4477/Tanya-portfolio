package com.tanya.repository;

import java.util.ArrayList;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tanya.model.Customer;

@Repository("customerRepository")
public class HibernateCustomerRepositoryImpl implements CustomerRepository {

	/*
	 * (non-Javadoc)
	 * 
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
