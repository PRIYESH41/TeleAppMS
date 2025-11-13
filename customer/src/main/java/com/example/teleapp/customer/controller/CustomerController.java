package com.example.teleapp.customer.controller;

import java.util.concurrent.ExecutionException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.teleapp.customer.dto.CustomerDTO;
import com.example.teleapp.customer.dto.LoginDTO;
import com.example.teleapp.customer.service.CustomerService;

@RestController
@CrossOrigin
public class CustomerController {

	private static final Log LOGGER = LogFactory.getLog(CustomerController.class);

	@Autowired
	CustomerService custService;

	// Create a new customer
	@PostMapping(value = "/customers",  consumes = MediaType.APPLICATION_JSON_VALUE)
	public void createCustomer(@RequestBody CustomerDTO custDTO) {
		LOGGER.info("Creation request for customer "+ custDTO);
		custService.createCustomer(custDTO);
	}

	// Login
	@PostMapping(value = "/login",consumes = MediaType.APPLICATION_JSON_VALUE)
	public boolean login(@RequestBody LoginDTO loginDTO) {
		LOGGER.info("Login request for customer "+loginDTO.getPhoneNo()+" with password "+loginDTO.getPassword());
		return custService.login(loginDTO);
	}

	// Fetches full profile of a specific customer
	@GetMapping(value = "/customers/{phoneNo}", produces = MediaType.APPLICATION_JSON_VALUE)
	public CustomerDTO getCustomerProfile(@PathVariable Long phoneNo) throws InterruptedException, ExecutionException {

		LOGGER.info("Profile request for customer "+ phoneNo);
		return custService.getCustomerProfile(phoneNo);
	}



}
