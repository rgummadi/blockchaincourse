package com.upgrad.ImageHoster.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upgrad.ImageHoster.model.User;
import com.upgrad.ImageHoster.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	public Boolean registerUser(User newUser) {
		String password = newUser.getPassword();
		if(isPasswordValid(password)) {
			repository.registerUser(newUser);
			return true;
		} else {
			return false;
		}
		
		}
	
	public User login(User user) {

		   User existingUser = repository.checkUser(user.getUsername(), user.getPassword());
		   if(existingUser != null) {
		       return existingUser;
		   }
		   else {
		       return null;
		   }
		}

	public Boolean isPasswordValid(String password) {
		String pattern = "((?=.*\\d)(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9]).*)";
		System.out.println(password.matches(pattern));
		if(password.matches(pattern))
			return true;
		else
			return false;
		
	}
}
