package com.naver.myhome.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.naver.myhome.model.BbsBean;

@Controller
public class LoginController {

	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String login() {
		return "login/loginForm";
	}
	
	@RequestMapping(value = "/login_ok.do", method = RequestMethod.POST)
	public ModelAndView login_ok(HttpServletRequest request, ModelAndView mv) {
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");

		BbsBean bbs = new BbsBean();
		bbs.setId(id);
		bbs.setPass(pass);

		mv.setViewName("login/list");
		mv.addObject("bkey", bbs);

		return mv;
	}

	@RequestMapping(value="/login_ok1.do",method=RequestMethod.POST)
	public String loing_ok1(HttpServletRequest request, Model mv) {
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");

		BbsBean bbs = new BbsBean();
		bbs.setId(id);
		bbs.setPass(pass);

		mv.addAttribute("bkey", bbs);

		return "login/list";
	}
	
	@RequestMapping(value = "/login2", method = RequestMethod.GET)
	public String login2() {
		return "login/loginForm2";
	}
	
	@RequestMapping(value="/login_ok2.do", method=RequestMethod.POST)
	public String login_ok2(BbsBean bbs) throws Exception{
		return "login/list2";
	}
	
	@RequestMapping(value = "/login3", method = RequestMethod.GET)
	public String login3() {
		return "login/loginForm3";
	}
	
	@RequestMapping(value="/login_ok3.do", method=RequestMethod.POST)
	public String login_ok3(@ModelAttribute("hoho") BbsBean bbs) throws Exception{
		return "login/list3";
	}
	
}