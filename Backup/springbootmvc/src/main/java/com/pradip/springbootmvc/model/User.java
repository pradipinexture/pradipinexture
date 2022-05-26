package com.pradip.springbootmvc.model;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.pradip.springbootmvc.util.EncryDecryAES;

@Component
@Entity
@Table(name = "spring_user_table")
public class User {
	@Id
	@GeneratedValue
	private int id;
	
	@NotBlank(message = "Please fill name")
	@Column(nullable = false)
	private String name;
	
	@NotBlank(message = "Please fill mobile number.")
	@Pattern(regexp="(^$|[0-9]{10})",message = "Please enter 10 digit mobile number only")
	@Column(length = 10)
	private String mobile;
	@Column(nullable = false,unique = true)
	
	@NotBlank(message = "Please fill email.")
	@Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}", flags = Pattern.Flag.CASE_INSENSITIVE)
	private String email;

	@NotBlank(message = "Please enter birthdate.")
	@Column(nullable = false)
	private String birthdate;
	
	@NotBlank(message = "Please enter gender.")
	@Column(nullable = false)
	private String gender;
	
	@NotBlank(message = "Please enter hobby.")
	@Column(nullable = false)
	private String hobby;
	
	@NotBlank(message = "Please fill password.")
	@Column(nullable = false)
	private String password;
	
	@Transient
	private MultipartFile image;
	
	@Lob
	@Column(name="image")
	private String imageBase64;
	
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	List<Address> address;

	@OneToOne(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	Role role;
	
	public User() {
		super();
	}
	
	public User(int id, String name, String mobile, String email, String birthdate, String gender, String hobby,
			String password) {
		super();
		this.id = id;
		this.name = name;
		this.mobile = mobile;
		this.email = email;
		this.birthdate = birthdate;
		this.gender = gender;
		this.hobby = hobby;
		this.password = password;
		
	}
	
	
	
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}


	public List<Address> getAddress() {
		return address;
	}



	public void setAddress(List<Address> address) {
		this.address = address;
	}

	public String getImageBase64() {
		return imageBase64;
	}

	public void setImageBase64(String imageBase64) {
		this.imageBase64 = imageBase64;
	}
	public MultipartFile getImage() {
		return image;
	}

	public void setImage(MultipartFile image) {
		try {
			this.imageBase64 = Base64.getEncoder().encodeToString(image.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getHobby() {
		return hobby;
	}
	public void setHobby(String hobby) {
		this.hobby = hobby;
	}
	public String getPassword() {
		return EncryDecryAES.encDecryAESAlgo(password, "decrypt");
	}
	public void setPassword(String password) {
		this.password = EncryDecryAES.encDecryAESAlgo(password, "encrypt");//password;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", mobile=" + mobile + ", email=" + email + ", birthdate="
				+ birthdate + ", gender=" + gender + ", hobby=" + hobby + " password=" + getPassword() + ", address=" + address + ", role=" + role+ "]";
	}
}
