package com.example.teleapp.plan.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.teleapp.plan.entity.Plan;

public interface PlanRepository extends JpaRepository<Plan, Integer> {
	


}
