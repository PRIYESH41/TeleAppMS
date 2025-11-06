package com.example.teleapp.calldetails.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.teleapp.calldetails.dto.CallDetailsDTO;
import com.example.teleapp.calldetails.entity.CallDetails;
import com.example.teleapp.calldetails.repository.CallDetailsRepository;

@Service
public class CallDetailsService {
	private static final Log LOGGER = LogFactory.getLog(CallDetailsService.class);

	@Autowired
	CallDetailsRepository callDetailsRepo;

	// Fetches call details of a specific customer
	public List<CallDetailsDTO> getCustomerCallDetails(@PathVariable long phoneNo) {

		LOGGER.info("Calldetails request for customer "+ phoneNo);

		List<CallDetails> callDetails = callDetailsRepo.findByCalledBy(phoneNo);
		List<CallDetailsDTO> callsDTO = new ArrayList<>();

		for (CallDetails call : callDetails) {
			callsDTO.add(CallDetailsDTO.valueOf(call));
		}
		LOGGER.info("Calldetails for customer : "+ callDetails);

		return callsDTO;
	}
}
