package com.example.teleapp.customer.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.example.teleapp.customer.dto.PlanDTO;


@FeignClient(name ="plan-ms",url = "${gateway.uri}")
public interface PlanFeignService {
	
	@GetMapping("/plans/{planId}")
	public PlanDTO getPlan(@PathVariable("planId") Integer planId);
}
