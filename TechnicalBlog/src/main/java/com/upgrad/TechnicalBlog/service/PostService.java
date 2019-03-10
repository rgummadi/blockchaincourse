package com.upgrad.TechnicalBlog.service;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.upgrad.TechnicalBlog.model.Post;

@Service
public class PostService {
	
	public PostService () {
		System.out.println("Post Service");
	}
	public ArrayList<Post> getAllPosts() {
		
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
		
		return posts;
		
	}
	
	public ArrayList<Post> getOnePost() {
		ArrayList<Post> posts = new ArrayList<>();
		Post post1 = new Post();
		post1.setTitle("This is your first post");
		post1.setBody("This is your first post");
		post1.setDate(new Date());
		
		posts.add(post1);
		return posts;
	}
	
	public void createPost (Post newPost){
	}
	
}
