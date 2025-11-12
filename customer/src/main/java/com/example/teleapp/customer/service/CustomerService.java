package com.example.teleapp.customer.service;

import java.util.List;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;

import com.example.teleapp.customer.dto.CustomerDTO;
import com.example.teleapp.customer.dto.LoginDTO;
import com.example.teleapp.customer.dto.PlanDTO;
import com.example.teleapp.customer.entity.Customer;
import com.example.teleapp.customer.repository.CustomerRepository;

@Service
//@LoadBalancerClient(name = "friend-ms",configuration=LoadBalancerConfig.class)
public class CustomerService {
	private static final Log LOGGER = LogFactory.getLog(CustomerService.class);

	@Autowired
	CustomerRepository custRepo;
	
	@Autowired
	DiscoveryClient discoveryClient;
	
	@Autowired
	RemoteCallService remoteCallService;
	
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
		long overAllStart = System.currentTimeMillis();
		CustomerDTO custDTO = null;
		LOGGER.info("Profile request for customer "+ phoneNo);
		Optional<Customer> optCust = custRepo.findById(phoneNo);
		
		if (optCust.isPresent()) {
			Customer cust = optCust.get();
			custDTO = CustomerDTO.valueOf(cust);
			
			//String planUri = getUriFromDiscoveryClient("PlanMS");			
			
			LOGGER.info("Fetching Plan detail remote call");
			long planStart = System.currentTimeMillis();
			PlanDTO planDTO = remoteCallService.getSpecificPlan(custDTO.getCurrentPlan().getPlanId());
			long planStop = System.currentTimeMillis();
			custDTO.setCurrentPlan(planDTO);
			
			LOGGER.info("Fetching friends and family number remote call");
			long friendStart = System.currentTimeMillis();
			List<Long> friends=remoteCallService.getSpecificFriends(phoneNo);
			long friendStop = System.currentTimeMillis();
			custDTO.setFriendAndFamily(friends);
			
			long overAllStop = System.currentTimeMillis();
			LOGGER.info("Total time for Plan "+(planStop-planStart));
			LOGGER.info("Total time for Friend "+(friendStop-friendStart));
			LOGGER.info("Total Overall time for Request "+(overAllStop-overAllStart));
			
		}

		LOGGER.info("Profile for customer : "+ custDTO);
		return custDTO;
	}
	
	
	private String getUriFromDiscoveryClient(String serviceId) {
		  LOGGER.info("Fetching Uri from Discovery Server for serviceId : " + serviceId );
		  List<ServiceInstance>	serviceInstances = discoveryClient.getInstances(serviceId);
		  String uri = "";
		  if(serviceInstances != null && !serviceInstances.isEmpty()) {
			  uri = serviceInstances.get(0).getUri().toString();
		  }	
		  return uri;
	}
	
	public CustomerDTO getCustomerProfileFallback(Long phoneNo,Throwable throwable) {
		LOGGER.info("***************IN FALLBACK********************");
		return new CustomerDTO();
	}

}
