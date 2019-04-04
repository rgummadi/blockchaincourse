package com.upgrad.ImageHoster.Controller;

import java.time.LocalDate;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.upgrad.ImageHoster.model.Comment;
import com.upgrad.ImageHoster.model.User;
import com.upgrad.ImageHoster.service.CommentService;
import com.upgrad.ImageHoster.service.ImageService;

@Controller
public class CommentController {

	@Autowired
	private CommentService commentService;
	
	@Autowired
	private ImageService imageService;
	
	@RequestMapping(value = "/image/{imageId}/{imageTitle}/comments", method = RequestMethod.POST)
	 public String createComment(@PathVariable("imageId") Integer imageId,@PathVariable("imageTitle") String title,@RequestParam("comment") String comment,Comment newComment, HttpSession session) {
		 User user = (User) session.getAttribute("loggeduser");
	        newComment.setUser(user);
	        newComment.setImage(imageService.getImage(imageId));   
	        newComment.setCreatedDate(LocalDate.now());
	        newComment.setText(comment);
	        commentService.createComment(newComment);
	        return "redirect:/images/" + imageId + "/" + title;
    }
}
