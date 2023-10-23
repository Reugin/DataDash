package com.datadash.app.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.datadash.app.model.User;


public interface UserRepository extends MongoRepository<User, String>{
	
	User findByEmail(String emailId);
    
    boolean existsByEmail(String emailId);
}

