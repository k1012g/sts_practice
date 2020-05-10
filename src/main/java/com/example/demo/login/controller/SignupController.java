package com.example.demo.login.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;

import com.example.demo.login.domain.service.UserService;
import com.example.demo.login.domain.model.GroupOrder;
import com.example.demo.login.domain.model.SignupForm;
import com.example.demo.login.domain.model.User;

@Controller
public class SignupController {
	@Autowired
	private UserService userService;
	
//	ラジオボタンの実装
//	key = String, value = StringのMap
	private Map<String, String> radioMarriage;
	
//	ラジオボタンの初期化
	private Map<String, String> initRadioMarriage() {
		Map<String, String> radio = new LinkedHashMap<>();
		
//		二つの値をセット
		radio.put("既婚", "true");
		radio.put("未婚", "false");
		
		return radio;
	}
	
//	GetMappingアノテーションで/signupにGET
//	ModelAttributeアノテーションをつけると
//	自動でSignupFormというkeyにformというvalueをセットしてくれる
	@GetMapping("/signup")
	public String getSignUp(@ModelAttribute SignupForm form, Model model) {
//		ラジオボタンを初期化
		radioMarriage = initRadioMarriage();
		
//		singup.htmlで使うためにkeyとvalueをセット
		model.addAttribute("radioMarriage", radioMarriage);
		
//		src/main/resources/login/signup.htmlに遷移
		return "login/signup";
	}
	
//	/signupにPOSTした内容を受け取る
	@PostMapping("/signup")
	public String postSignUp(@ModelAttribute @Validated(GroupOrder.class) SignupForm form, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return getSignUp(form, model);
		}
		
		System.out.println(form);
		
		User user = new User();
		
		user.setUserId(form.getUserId());
		user.setPassword(form.getPassword());
		user.setUserName(form.getUserName());
		user.setBirthday(form.getBirthday());
		user.setAge(form.getAge());
		user.setMarriage(form.getMarriage());
		user.setRole("ROLE_GENERAL");
		
		boolean result = userService.insert(user);
		
		if (result == true) {
			System.out.println("insert成功");
		}else {
			System.out.println("insert失敗");
		}
		
		return "redirect:/login";
	}
	
	@ExceptionHandler(DataAccessException.class)
	public String dataAccessExceptionHandler(DataAccessException e, Model model) {
		model.addAttribute("error", "内部サーバーエラー(DB) : ExceptionHandler");
		
		model.addAttribute("message", "SignupControllerでDataAccessExceptionが発生しました。");
		
		model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR);
		
		return "error";
	}
	
	@ExceptionHandler(Exception.class)
	public String exceptionHandler(Exception e, Model model) {
		model.addAttribute("error", "内部サーバーエラー(DB) : ExceptionHandler");
		
		model.addAttribute("message", "SignupControllerでExceptionが発生しました。");
		
		model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR);
		
		return "error";
	}
}