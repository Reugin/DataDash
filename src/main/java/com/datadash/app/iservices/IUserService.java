package com.datadash.app.iservices;

import org.springframework.stereotype.Service;

import com.datadash.app.dto.LoginDTO;
import com.datadash.app.dto.UserRegistrationDTO;

import jakarta.validation.Valid;

@Service
public interface IUserService {
	
	void registerUser(@Valid UserRegistrationDTO registrationDTO);

	String loginUser(@Valid LoginDTO loginDTO);

}
