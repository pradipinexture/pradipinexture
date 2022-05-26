package com.pradip.springbootmvc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.ManyToAny;
import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "spring_address_table")
public class Address {
	
	// Neddable variable fields
	@Id
	@GeneratedValue
	private int id;
	
	@NotBlank(message = "Please fill general address")
	@Column(nullable = false)
	private String general;
	
	@NotBlank(message = "Please fill city address")
	@Column(nullable = false)
	private String city;
	
	@NotBlank(message = "Please fill state address")
	@Column(nullable = false)
	private String state;
	
	@NotBlank(message = "Please fill pincode address")
	@Column(nullable = false)
	private String pincode;
	
	@ManyToOne
	@JoinColumn(name="user_fk")
	private User user;
	
	// Below methods for getter setter

	
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getGeneral() {
		return general;
	}
	public void setGeneral(String general) {
		this.general = general;
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
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "Address [id=" + id + ", general=" + general + ", city=" + city + ", state=" + state + ", pincode="
				+ pincode + "]";
	}
}
