package com.upgrad.TechnicalBlog.controller;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.upgrad.TechnicalBlog.model.Post;
import com.upgrad.TechnicalBlog.service.PostService;

@Controller
public class HomeController {
	
	public HomeController() {
		// TODO Auto-generated constructor stub
		System.out.println("HomeController");
	}
	
	@Autowired
	private PostService postService;
	
	@RequestMapping("/")
	public String getAllPosts(Model model) {
		
		ArrayList<Post> posts = postService.getAllPosts();
		
		model.addAttribute("posts",posts);
		
		return "index";
	}

}
