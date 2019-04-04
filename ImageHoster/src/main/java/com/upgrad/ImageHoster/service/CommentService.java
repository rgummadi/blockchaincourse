package com.upgrad.ImageHoster.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upgrad.ImageHoster.model.Comment;
import com.upgrad.ImageHoster.repository.CommentRepository;

@Service
public class CommentService {
	 @Autowired
	 private CommentRepository commentRepository;
	public void createComment(Comment newComment) {
		// TODO Auto-generated method stub
		commentRepository.createComment(newComment);
	}

}
