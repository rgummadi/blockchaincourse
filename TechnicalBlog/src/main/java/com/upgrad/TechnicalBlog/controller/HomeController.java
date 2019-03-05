package com.upgrad.TechnicalBlog.controller;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.upgrad.TechnicalBlog.model.Post;

@Controller
public class HomeController {
	
	@RequestMapping("/")
	public String getAllPosts(Model model) {
		
		ArrayList<Post> posts = new ArrayList<>();
		Post post1 = new Post();
		post1.setTitle("Psot1");
		post1.setBody("postBody 1");
		post1.setDate(new Date());
		
		Post post2 = new Post();
		post2.setTitle("Psot1");
		post2.setBody("postBody 1");
		post2.setDate(new Date());
		
		Post post3 = new Post();
		post3.setTitle("Psot1");
		post3.setBody("postBody 1");
		post3.setDate(new Date());
		
		posts.add(post1);
		posts.add(post2);
		posts.add(post3);
		
		model.addAttribute("posts",posts);
		
		return "index";
	}

}
