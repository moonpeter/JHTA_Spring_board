package com.naver.myChat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ChatController {
	
	@Autowired
	private IService memberService;
	
	@Value("${savefoldername}")
	private String saveFolder;
	
	private static final Logger logger = LoggerFactory.getLogger(ChatController.class); 
	
	@GetMapping("/login")
	public String login(HttpSession session) {
		logger.info("login()");
		return "login_sample";
	}
	
	@GetMapping("/join")
	public String join() {
		logger.info("void join()");
		return "join_sample";
	}
	
	@PostMapping("/login_ok.do")
	public String loginOk(String name, Model m, HttpServletRequest request) {
		logger.info("reqeust url2=" + request.getRequestURL());
		int end = request.getRequestURL().lastIndexOf("/");
		String url = request.getRequestURL().substring(7, end);
		logger.info("url=" + url);
		m.addAttribute("url", url);
		m.addAttribute("name", name);
		return "websocket";
	}
	
	
}
