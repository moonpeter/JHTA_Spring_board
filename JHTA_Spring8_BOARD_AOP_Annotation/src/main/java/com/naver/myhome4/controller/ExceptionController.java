package com.naver.myhome4.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;


@Controller
@ControllerAdvice
public class ExceptionController {

	private static final Logger logger = LoggerFactory.getLogger(ExceptionController.class);
	
	@ExceptionHandler(Exception.class)
	public ModelAndView common(Exception e, HttpServletRequest request) {
		logger.info(e.toString());
		ModelAndView mav = new ModelAndView();
		mav.setViewName("error/common");
		mav.addObject("exception", e);
		mav.addObject("url", request.getRequestURL());
		return mav;
	}
	
	@ExceptionHandler(NoHandlerFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ModelAndView handleError404(Exception e, HttpServletRequest request) {
		logger.info(e.toString());
		ModelAndView mav = new ModelAndView();
		mav.setViewName("error/404");
		mav.addObject("exception", "404 오류 발생했습니다.");
		mav.addObject("url", request.getRequestURL());
		return mav;
	}
}
