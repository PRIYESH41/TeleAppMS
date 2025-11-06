package com.example.teleapp.friend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.teleapp.friend.entity.FriendFamily;

public interface FriendFamilyRepository extends JpaRepository<FriendFamily, Integer> {

	List<FriendFamily> findByPhoneNo(long phoneNo);
}
