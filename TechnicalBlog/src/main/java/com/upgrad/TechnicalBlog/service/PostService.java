package com.upgrad.TechnicalBlog.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
		
		
		/*Post post1 = new Post();
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
		posts.add(post3);*/
		Connection connection = null;
		try {
			Class.forName("org.postgresql.Driver");
			
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/technicalblog","postgres", "");
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM posts");
			while(rs.next()) {
				Post post = new Post();
				post.setTitle(rs.getString("title"));
				post.setBody(rs.getString("body"));
				posts.add(post);
				
			}
			
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			   try{
			       connection.close();
			       }catch(SQLException e){
			      e.printStackTrace();
			       }
			       }
		
		return posts;
		
	}
	
	public ArrayList<Post> getOnePost() {
		ArrayList<Post> posts = new ArrayList<>();
		
		
		/*Post post1 = new Post();
		post1.setTitle("This is your first post");
		post1.setBody("This is your first post");
		post1.setDate(new Date());*/
		Connection connection = null;
		try {
			Class.forName("org.postgresql.Driver");
			
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/technicalblog","postgres", "");
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM posts where id = 4");
			while(rs.next()) {
				Post post = new Post();
				post.setTitle(rs.getString("title"));
				post.setBody(rs.getString("body"));
				posts.add(post);
				
			}
			
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		finally{
			   try{
			       connection.close();
			       }catch(SQLException e){
			      e.printStackTrace();
			       }
			       }
		
		return posts;
	}
	
	public void createPost (Post newPost){
	}
	
}
