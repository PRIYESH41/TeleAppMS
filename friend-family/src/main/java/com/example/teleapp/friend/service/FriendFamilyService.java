package com.example.teleapp.friend.service;

import java.util.ArrayList;
import java.util.List;

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
	
	
	// Get friend and family phone number list of a given customer
	public List<Long> getSpecificFriends(Long phoneNo){
		LOGGER.info("Friend and family detailsfor customer "+ phoneNo);
		List<Long> friendList= new ArrayList<>();
		List<FriendFamily> friends=friendRepo.findByPhoneNo(phoneNo);
		for (FriendFamily friendFamily : friends) {
			friendList.add(friendFamily.getFriendAndFamily());
		}
		LOGGER.info("The friend list is for customer"+phoneNo+" is "+friendList);
		return friendList;
	}

}
