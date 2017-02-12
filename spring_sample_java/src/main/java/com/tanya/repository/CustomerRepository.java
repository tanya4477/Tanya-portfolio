package com.tanya.repository;

import java.util.List;

import com.tanya.model.Customer;

public interface CustomerRepository {

	List<Customer> findAll();

}