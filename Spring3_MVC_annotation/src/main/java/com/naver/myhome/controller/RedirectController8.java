package com.naver.myhome.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RedirectController8 {
	
	@RequestMapping(value="/first")
	public String first(RedirectAttributes rattr) throws Exception {
		rattr.addFlashAttribute("flash", "addFlashAttribute");
		rattr.addAttribute("attr", "addAttribute");
		return "redirect:/go";
	}
	
	@RequestMapping(value="/go")
	public String redirect(RedirectAttributes rattr) throws Exception {
		return "redirect/go";
	}
}
