package com.naver.myhome4.controller;

import java.io.PrintWriter;
import java.security.Principal;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.naver.myhome4.domain.MailVO;
import com.naver.myhome4.domain.Member;
import com.naver.myhome4.service.MemberService;
import com.naver.myhome4.task.SendMail;

@Controller
@RequestMapping(value = "/member")
public class MemberController {
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

	@Autowired
	private MemberService memberservice;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private SendMail sendMail;

	// 회원가입 폼 이동
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join() {
		return "member/member_joinForm";
	}

	// 회원가입폼에서 아이디 검사
	@RequestMapping(value = "/idcheck", method = RequestMethod.GET)
	public void idcheck(@RequestParam("id") String id, HttpServletResponse response) throws Exception {
		int result = memberservice.isId(id);
		response.setContentType("text/html);charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(result);
	}

	@RequestMapping(value = "/joinProcess", method = RequestMethod.POST)
	public String joinProcess(Member member, RedirectAttributes rattr, Model model, HttpServletRequest request)
			throws Exception {
		
		// 비밀번호 암호화 추가 
		String encPassword = passwordEncoder.encode(member.getPassword());
		logger.info(encPassword);
		member.setPassword(encPassword);
		
		int result = memberservice.insert(member);

		if (result == 1) {
			MailVO vo = new MailVO();
			vo.setTo(member.getEmail());
			vo.setContent(member.getId() + "님 회원가입을 축하드립니다. ");
			sendMail.sendMail(vo);
			
			rattr.addFlashAttribute("result", "joinSuccess");
			return "redirect:login";
		} else {
			model.addAttribute("url", request.getRequestURL());
			model.addAttribute("message", "회원가입 실패");
			return "error/error";
		}
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(ModelAndView mv, @CookieValue(value = "remember-me", required = false) Cookie readCookie, Model model, HttpSession session, Principal principal) {
		model.addAttribute("loginFailMsg", session.getAttribute("loginFailMsg"));
		session.removeAttribute("loginFailMsg");
		if (readCookie != null) {
			logger.info("저장된 아이디" + principal.getName());
			return "redirect:/board/list";
		}
		return "member/member_loginForm";
	}

//	@RequestMapping(value = "/loginProcess", method = RequestMethod.POST)
	public String loginProcess(@RequestParam("id") String id, @RequestParam("password") String password,
			@RequestParam(value = "remember", defaultValue = "", required = false) String remember,
			HttpServletResponse response, HttpSession session, RedirectAttributes rattr) {

		int result = memberservice.isId(id, password);
		logger.info("결과 : " + result);

		if (result == 1) {
			// 로그인 성공
			session.setAttribute("id", id);
			Cookie savecookie = new Cookie("saveid", id);
			if (!remember.equals("")) {
				savecookie.setMaxAge(60 * 60);
				logger.info("쿠키저장 : 60*60");
			} else {
				logger.info("쿠키저장 : 0");
				savecookie.setMaxAge(0);
			}
			response.addCookie(savecookie);
			return "redirect:/board/list";
		} else {
			rattr.addFlashAttribute("result", result);
			return "redirect:login";
		}
	}

	@GetMapping("/update")
	public ModelAndView memberUpdate(HttpSession session, ModelAndView mv) {
		String id = (String) session.getAttribute("id");
		if (id == null) {
			mv.setViewName("redirect:login");
		} else {
			Member memberdata = memberservice.member_info(id);
			mv.addObject("memberdata", memberdata);
			mv.setViewName("member/member_updateForm");
		}
		return mv;
	}

	@PostMapping("/updateProcess")
	public String updateProcess(Member member, Model model, HttpServletRequest request, RedirectAttributes rattr) {
		int result = memberservice.update(member);
		if (result == 1) {
			rattr.addFlashAttribute("result", "updateSuccess");
			return "redirect:/board/list";
		} else {
			model.addAttribute("url", request.getRequestURL());
			model.addAttribute("message", "정보 수정 실패");
			return "error/error";
		}
	}

	@GetMapping("/list")
	public ModelAndView memberList(@RequestParam(value = "page", defaultValue = "1", required = false) int page,
			@RequestParam(value = "limite", defaultValue = "3", required = false) int limit, ModelAndView mv,
			@RequestParam(value = "search_field", defaultValue = "", required = false) String index,
			@RequestParam(value = "search_word", defaultValue = "", required = false) String search_word) {
		List<Member> list = null;
		int listcount = 0;

		list = memberservice.getSearchList(index, search_word, page, limit);
		listcount = memberservice.getSearchListcount(index, search_word); // 총 리스트 수를 받아옴

		// 총 페이지 수
		int maxpage = (listcount + limit - 1) / limit;

		// 현재 페이지에 보여줄 시작 페이지 수
		int startpage = ((page - 1) / 10) * 10 + 1;

		// 현재 페이지에 보여줄 마지막 페이지 수
		int endpage = startpage + 10 - 1;

		if (endpage > maxpage)
			endpage = maxpage;

		mv.setViewName("member/member_list");
		mv.addObject("page", page);
		mv.addObject("maxpage", maxpage);
		mv.addObject("startpage", startpage);
		mv.addObject("endpage", endpage);
		mv.addObject("listcount", listcount);
		mv.addObject("memberlist", list);
		mv.addObject("search_field", index);
		mv.addObject("search_word", search_word);
		return mv;
	}

	@GetMapping("/info")
	public ModelAndView memberInfo(String id, ModelAndView mv, HttpServletRequest request) {
		Member memberdata = memberservice.member_info(id);
		if (memberdata != null) {
			mv.addObject("memberinfo", memberdata);
			mv.setViewName("member/member_info");
		} else {
			mv.addObject("url", request.getRequestURL());
			mv.addObject("message", "해당 정보가 없습니다.");
			mv.setViewName("error/error");
		}
		return mv;
	}

	@GetMapping("/delete")
	public String delete(String id) {
		memberservice.delete(id);
		return "redirect:list";
	}
	
//	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:login";
	}
	
}
