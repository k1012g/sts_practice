package com.example.demo.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

//ControllerアノテーションをつけてDIコンテナで管理
@Controller
public class LoginController {
	
//	GetMappingアノテーションで/loginにGET
	@GetMapping("/login")
	public String getLogin(Model model) {
//		src/resources/templates/login/login.htmlに遷移
		return "login/login";
	}
	
//	PostMappingアノテーションで/loginにPOSTした内容を受け取る
	@PostMapping("/login")
	public String postLogin(Model model) {
//		src/resources/templates/login/home.htmlに遷移
		return "redirect:/home";
	}
}