package com.upgrad.ImageHoster.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.upgrad.ImageHoster.model.Image;
import com.upgrad.ImageHoster.service.ImageService;

@Controller
public class HomeController {

	@Autowired
	private ImageService imageService;
	
	@RequestMapping("/")
	public String getAllImages(Model model) {
		
		List<Image> images = imageService.getAllImages();
		model.addAttribute("images",images);
		return "index";
	}
}
