package com.tanya.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tanya.model.Customer;
import com.tanya.repository.CustomerRepository;

@Service("customerService")
public class CustomerServiceImpl implements CustomerService {

	// @Autowired
	private CustomerRepository customerRepository;

	@Autowired
	public CustomerServiceImpl(CustomerRepository customerRepository) {

		System.out.println("We are using constructor injection");
		this.customerRepository = customerRepository;
	}

	@Autowired
	public void setCustomerRepository(CustomerRepository customerRepository) {
		System.out.println("we are using setter injection");
		this.customerRepository = customerRepository;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.tanya.service.CustomerService#findAll()
	 */
	@Override
	public List<Customer> findAll() {
		return customerRepository.findAll();
	}
}
