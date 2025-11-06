package com.example.teleapp.customer.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;


@Entity
public class Customer {

	@Id
	@Column(name = "phone_no", nullable = false)
	Long phoneNo;
	@Column(nullable = false, length = 50)
	String name;
	@Column(nullable = false)
	Integer age;
	@Column(nullable = false, length = 50)
	String address;
	@Column(nullable = false, length = 50)
	String password;
	@Column(nullable = false, length = 1)
	char gender;
	
	@OneToOne(cascade=CascadeType.REFRESH)
	@JoinColumn(name="plan_id")
	Plan plan;

	@OneToMany(cascade=CascadeType.MERGE)
	@JoinColumn(name="phone_no")
	List<FriendFamily> friends=new ArrayList<>();
	
	
	public Plan getPlan() {
		return plan;
	}

	public void setPlan(Plan plan) {
		this.plan = plan;
	}

	public List<FriendFamily> getFriends() {
		return friends;
	}

	public void setFriends(List<FriendFamily> friends) {
		this.friends = friends;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public Long getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(Long phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Customer() {
		super();
	}
}
