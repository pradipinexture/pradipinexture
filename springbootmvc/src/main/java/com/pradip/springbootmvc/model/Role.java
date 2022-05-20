package com.pradip.springbootmvc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.springframework.stereotype.Component;
@Component
@Entity
@Table(name = "spring_role_table")
public class Role {
	@Override
	public String toString() {
		return "Role [id=" + id + ", roleType=" + roleType + "]";
	}
	@Id
	@GeneratedValue
	private int id;
	
	@Column(nullable = false)
	private int roleType;
	@OneToOne
	private User user;
	
	public Role(int id, int roleType, User user) {
		super();
		this.id = id;
		this.roleType = roleType;
		this.user = user;
	}
	public Role() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getRoleType() {
		return roleType;
	}
	public void setRoleType(int roleType) {
		this.roleType = roleType;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
}
