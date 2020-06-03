package com.hello.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hello.business.Pet;

@Controller
public class SampleController {
	
	@RequestMapping("/hello")
	@ResponseBody
	public String hello() {
		return "Hello Java Bootcamp!";
	}
	
	@RequestMapping("/pet")
	@ResponseBody
	public Pet displayPet() {
		Pet p = new Pet(1,"Sparky","Dog");
		return p;
	}

}
