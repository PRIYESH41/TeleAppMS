package com.example.teleapp.plan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.teleapp.plan.dto.PlanDTO;
import com.example.teleapp.plan.service.PlanService;

@RestController
@EnableAutoConfiguration
@CrossOrigin
public class PlanController {


	@Autowired
	PlanService planService;

	// Fetches all plan details
	@GetMapping(value = "/plans",  produces = MediaType.APPLICATION_JSON_VALUE)
	public List<PlanDTO> getAllPlans() {
		return planService.getAllPlans();
	}
	// To get a plan details based on plan id
	@GetMapping(value = "/plans/{planId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public PlanDTO getPlan(@PathVariable("planId") Integer planId) {
		try {
		    Thread.sleep(5000);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
		return planService.getPlan(planId);
	}


}
