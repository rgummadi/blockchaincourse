package com.upgrad.TechnicalBlog.service;


import java.util.Date;
import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upgrad.TechnicalBlog.model.Post;
import com.upgrad.TechnicalBlog.repository.PostRepository;

@Service
public class PostService {
	
	@Autowired
	private PostRepository repository;
	
		
	public PostService () {
		System.out.println("Post Service");
	}
	public List<Post> getAllPosts() {
		
		return repository.getAllPosts();
	}
	
	public Post getOnePost() {
		return repository.getLatestPost();
	}
	
	public void createPost (Post newPost){
		
		newPost.setDate(new Date());
		repository.createPost(newPost);
		System.out.println("New Post: "+ newPost);;
		
	}
	
	 public Post getPost(Integer postId) {
	        return repository.getPost(postId);
	    }
	 
	 public void updatePost(Post updatedPost) {
		   updatedPost.setDate(new Date());
		   repository.updatePost(updatedPost);
		}

	 public void deletePost(Integer postId) {
		   repository.deletePost(postId);
		}
}
