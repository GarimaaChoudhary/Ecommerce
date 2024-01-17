package com.garimachoudhary.ecommerceproject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Address {

	 @Id
	 @GeneratedValue(strategy=GenerationType.AUTO)
	 private Long id;
	 
	 @Column(name="first_name")
	 private String firstName;
	 
	 @Column(name="last_name")
	 private String lastName;
	 
	 @Column(name="street_address")
	 private String streetAddress;
	 
	 @Column(name="city")
	 private String city;
	 
	 @Column(name="state")
	 private String state;
	 
	 @ManyToOne
	 @JoinColumn(name="user_id")
	 @JsonIgnore
	 private User user;
	 
	 private String mobile;
	 private String zipCode;
	 public Address() {
	 
	 }

	public Address(Long id, String firstname, String lastname, String streetAddress, String city, String state,
			User user, String mobile,String zipCode) {
		super();
		this.id = id;
		this.firstName = firstname;
		this.lastName = lastname;
		this.streetAddress = streetAddress;
		this.city = city;
		this.state = state;
		this.user = user;
		this.mobile = mobile;
		this.zipCode=zipCode;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	 
}
