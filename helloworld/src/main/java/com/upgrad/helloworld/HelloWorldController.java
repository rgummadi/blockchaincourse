package com.upgrad.helloworld;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloWorldController {
	
	@RequestMapping("/hellospring")
	@ResponseBody
	public String helloSpring() {
		return "Welcome to Technical Blog";
	}

}
