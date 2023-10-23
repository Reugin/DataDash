package com.datadash.app.model;



import javax.persistence.Id;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import com.datadash.app.dto.UserRegistrationDTO;


@Document(collection = "users")
public class User {
	
	private static final long serialVersionUID = 1L;
	@Id
	public ObjectId _id;


    private String email;
    
    private String name;
    
    private String password;

	public User(UserRegistrationDTO registerDto) {
		super();
		this.email = registerDto.getEmail();
		this.name = registerDto.getName();
		this.password = registerDto.getPassword();
	}

	public User() {
		super();
	}



	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}