package com.example.teleapp.customer.service;

import java.util.Collections;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import com.example.teleapp.customer.dto.PlanDTO;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;


@Service
public class RemoteCallService {
	
	private static final Log LOGGER = LogFactory.getLog(RemoteCallService.class);

	@Autowired
	RestTemplate restTemplate;
	
	@CircuitBreaker(name = "customerService" , fallbackMethod = "getFriendsFallback")
	public List<Long> getSpecificFriends(Long phoneNo) {
		List<Long> friends = restTemplate.getForObject("http://FriendMS/customers/"+phoneNo+"/friends", List.class);
		return friends;
	}
	
	@CircuitBreaker(name = "customerService" , fallbackMethod = "getPlanFallback")
	public PlanDTO getSpecificPlan(Integer planId) {
		PlanDTO planDTO = restTemplate.getForObject("http://PlanMS/plans/"+planId, PlanDTO.class);
		return planDTO;
	}
		
	
	public List<Long> getFriendsFallback(Long phoneNo,Throwable throwable){
		LOGGER.info("*************GET FRIEND FALLBACK*******************");
		return Collections.emptyList();
	}
	
	public PlanDTO getPlanFallback(Integer planId,Throwable throwable){
		LOGGER.info("*****************GET PLAN FALLBACK********************");
		return new PlanDTO();
	} 
}
