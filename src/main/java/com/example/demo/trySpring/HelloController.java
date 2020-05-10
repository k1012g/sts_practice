package com.example.demo.trySpring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

// ControllerアノテーションをつけるとDIコンテナで管理される
@Controller
public class HelloController {
	
	@Autowired
	private HelloService helloService;

//	GetMappingアノテーションをつけると引数のURLにHTTPメソッドのGETでアクセス
	@GetMapping("/hello")
	public String getHello() {
//		引数のURLにHTTPメソッドのGETでアクセスしたときにsrc/main/resources/templates下のhello.htmlに遷移
		return "hello";
	}
	
//	PostMappingアノテーションをつけるとHTTPメソッドのPOSTの処理が可能
//	/helloにPOSTしたformの内容を取ってくる
//	RequestParamアノテーションでPOSTした内容を受け取る
	@PostMapping("/hello")
	public String postRequest(@RequestParam("text1")String str, Model model) {
//		sampleという文字列にtext1の内容をセット
//		helloResponseでth:text属性にsampleというkeyを使うとvalueを使うことができる
		model.addAttribute("sample", str);
//		src/main/resource/templates下のhelloResponse.htmlに遷移
		return "helloResponse";
	}
	
//	PostMappingアノテーションをつけるとHTTPメソッドのPOSTの処理が可能
//	/hello/dbにPOSTしたformの内容を取ってくる
//	RequestParamアノテーションでPOSTした内容を受け取る
	@PostMapping("/hello/db")
	public String postDbRequest(@RequestParam("text2")String str, Model model) {
//		text2の内容を整数型に変換
		int id = Integer.parseInt(str);
//		helloServiceのfindOneメソッド
		Employee employee = helloService.findOne(id);
		
//		keyにvalueをセット
//		helloResponseでth:text属性にkeyを使うとvalueを使うことができる
		model.addAttribute("id", employee.getEmployeeId());
		model.addAttribute("name", employee.getEmployeeName());
		model.addAttribute("age", employee.getAge());
		
//		src/main/resource/templates下のhelloResponseDB.htmlに遷移
		return "helloResponseDB";
	}
}