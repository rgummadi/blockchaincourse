package com.upgrad.ImageHoster.Contoller;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.upgrad.ImageHoster.model.User;
import com.upgrad.ImageHoster.model.UserProfile;
import com.upgrad.ImageHoster.service.UserService;


@Controller
public class UserController {

    @Autowired
    private UserService userService;

    //This controller method is called when the request pattern is of type 'users/registration'
    @RequestMapping("users/registration")
    public String registration(Model model) {
        //Complete this method
        //Observe User and UserProfile models implemented
        //Declare an object of User class and UserProfile class
        //Set the profile of the user as UserProfile type object
        //Add user in the model and return 'users/registration.html'
    	
    	User user = new User();
    	UserProfile profile = new UserProfile();
    	user.setProfile(profile);
    	
    	model.addAttribute("User", user);
    	return "users/registration";
    }

    //This controller method is called when the request pattern is of type 'users/registration' and also the incoming request is of POST type
    @RequestMapping(value = "users/registration", method = RequestMethod.POST)
    public String registerUser(User user) {
    	userService.registerUser(user);
 	   return "redirect:users/login";
    	
    }
    
    //This controller method is called when the request pattern is of type 'users/login'
    @RequestMapping("users/login")
    public String login() {
        //Complete this method to return the 'users/login.html'
    	return "/users/login";
    }

	@RequestMapping(value = "users/login",method=RequestMethod.POST)
	public String loginUser(User user,HttpSession session) {
		
		User existingUser = userService.login(user);
		if (existingUser != null) {
			session.setAttribute("loggeduser", existingUser);
		       return "redirect:/images";
		   } else {
		       return "/users/login";
		   }
			 
	}
    
    
    
}
