package com.example.teleapp.friend.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.teleapp.friend.dto.FriendFamilyDTO;
import com.example.teleapp.friend.service.FriendFamilyService;

@RestController
@CrossOrigin
public class FriendFamilyController {

	private static final Log LOGGER = LogFactory.getLog(FriendFamilyController.class);

	@Autowired
	FriendFamilyService friendService;

	// Create Friend Family
	@PostMapping(value = "/customers/{phoneNo}/friends", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void saveFriend(@PathVariable Long phoneNo, @RequestBody FriendFamilyDTO friendDTO) {
		LOGGER.info("Creation request for customer "+phoneNo+" with data "+ friendDTO);
		friendService.saveFriend(phoneNo, friendDTO);
	}

}
