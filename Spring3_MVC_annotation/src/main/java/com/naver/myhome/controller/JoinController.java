package com.naver.myhome.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.naver.myhome.model.MemberBean;

@Controller
public class JoinController {
	
	@RequestMapping(value="/join", method=RequestMethod.GET)
	public String Join() {
		return "join";
	}
	
	@RequestMapping(value="/joinProcess.net", method=RequestMethod.POST)
	public String Join_ok(MemberBean mbs) throws Exception {
		return "join_ok";
	}
}
