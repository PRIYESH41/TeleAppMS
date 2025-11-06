package com.example.teleapp.customer.service;

import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.teleapp.customer.dto.CustomerDTO;
import com.example.teleapp.customer.dto.LoginDTO;
import com.example.teleapp.customer.entity.Customer;
import com.example.teleapp.customer.repository.CustomerRepository;

@Service
public class CustomerService {
	private static final Log LOGGER = LogFactory.getLog(CustomerService.class);

	@Autowired
	CustomerRepository custRepo;

	public void createCustomer(CustomerDTO custDTO) {
		LOGGER.info("Creation request for customer "+ custDTO);
		Customer cust = custDTO.createEntity();
		custRepo.save(cust);
	}

	// Login

	public boolean login(LoginDTO loginDTO) {
		Customer cust = null;
		LOGGER.info("Login request for customer "+loginDTO.getPhoneNo() +" with password "+loginDTO.getPassword());
		Optional<Customer> optCust = custRepo.findById(loginDTO.getPhoneNo());
		if (optCust.isPresent()) {
			cust = optCust.get();
			if (cust.getPassword().equals(loginDTO.getPassword())) {
				return true;
			}
		}

		return false;
	}

	// Fetches full profile of a specific customer

	public CustomerDTO getCustomerProfile(Long phoneNo) {

		CustomerDTO custDTO = null;
		LOGGER.info("Profile request for customer "+ phoneNo);
		Optional<Customer> optCust = custRepo.findById(phoneNo);
		if (optCust.isPresent()) {
			Customer cust = optCust.get();
			custDTO = CustomerDTO.valueOf(cust);
		}

		LOGGER.info("Profile for customer : "+ custDTO);
		return custDTO;
	}

}
