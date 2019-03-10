package com.upgrad.ImageHoster.Contoller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping("/")
	public String getAllImages() {
		
		return "index";
	}
}
