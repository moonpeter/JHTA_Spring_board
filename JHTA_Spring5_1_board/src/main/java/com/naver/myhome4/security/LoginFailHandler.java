package com.naver.myhome4.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Service;

@Service
public class LoginFailHandler implements AuthenticationFailureHandler{

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			org.springframework.security.core.AuthenticationException exception) throws IOException, ServletException {
		
		HttpSession session = request.getSession();
		session.setAttribute("loginFailMsg", "아이디 또는 비번 오류 입니다.");
		String url = request.getContextPath()  + "/member/login";
		response.sendRedirect(url);
	}
}
