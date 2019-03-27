package com.upgrad.TechnicalBlog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upgrad.TechnicalBlog.model.User;
import com.upgrad.TechnicalBlog.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	
	public User login(User user) {

		   User existingUser = repository.checkUser(user.getUsername(), user.getPassword());
		   if(existingUser != null) {
		       return existingUser;
		   }
		   else {
		       return null;
		   }
		}
	 
	 public void registerUser(User newUser) {
		   repository.registerUser(newUser);
		}
}
