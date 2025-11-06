package com.example.teleapp.friend.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.teleapp.friend.dto.FriendFamilyDTO;
import com.example.teleapp.friend.entity.FriendFamily;
import com.example.teleapp.friend.repository.FriendFamilyRepository;

@Service
public class FriendFamilyService {
	private static final Log LOGGER = LogFactory.getLog(FriendFamilyService.class);
	
	@Autowired
	FriendFamilyRepository friendRepo;

	// Create Friend Family
	public void saveFriend(@PathVariable Long phoneNo, @RequestBody FriendFamilyDTO friendDTO) {
		LOGGER.info("Creation request for customer "+phoneNo+" with data "+ friendDTO);
		friendDTO.setPhoneNo(phoneNo);
		FriendFamily friend = friendDTO.createFriend();
		friendRepo.save(friend);
	}

}
