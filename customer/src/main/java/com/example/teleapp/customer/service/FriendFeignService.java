package com.example.teleapp.customer.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name ="friend-ms",url = "${gateway.uri}")
public interface FriendFeignService {

	@GetMapping("/customers/{phoneNo}/friends")
	public List<Long> getSpecificFriends(@PathVariable Long phoneNo);
}
