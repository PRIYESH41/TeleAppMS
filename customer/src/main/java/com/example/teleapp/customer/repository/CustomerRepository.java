package com.example.teleapp.customer.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.teleapp.customer.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {


}
