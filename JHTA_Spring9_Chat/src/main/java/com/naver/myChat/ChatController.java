package com.naver.myChat;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/chat")
public class ChatController {
	
	@GetMapping("/home")
	public String loginform() {
		return "loginForm";
	}
}
