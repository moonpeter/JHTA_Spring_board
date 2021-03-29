package com.naver.myhome.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ParamController7 {
	@RequestMapping(value="/param1.do", method=RequestMethod.POST)
	public String param1(@RequestParam(value="age") int age, Model model, HttpServletRequest request) {
		model.addAttribute("age", age);
		model.addAttribute("url", request.getRequestURI());
		return "param/list1";
	}
	
	@RequestMapping(value="/param2.do", method=RequestMethod.POST)
	public String param2(@RequestParam(value="age2") int age, Model model, HttpServletRequest request) {
		model.addAttribute("age", age);
		return "param/list2";
	}
	
	@RequestMapping(value="/param3.do", method=RequestMethod.POST)
	public String param3(@RequestParam(value="age2", required=false) int age, Model model, HttpServletRequest request) {
		model.addAttribute("age", age);
		return "param/list2";
	}
	
	@RequestMapping(value="/param4.do", method=RequestMethod.POST)
	public String param4(@RequestParam(value="age2", defaultValue="10", required=false) int age, Model model, HttpServletRequest request) {
		model.addAttribute("age", age);
		return "param/list2";
	}
	
	@RequestMapping(value="/param5.do", method=RequestMethod.POST)
	public String param5(int age, Model model, HttpServletRequest request) {
		model.addAttribute("age", age);
		model.addAttribute("url", request.getRequestURI());
		return "param/list1";
	}
}
