package com.naver.myChat;

import java.io.File;
import java.util.Calendar;
import java.util.Map;
import java.util.Random;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.naver.myChat.bootstrap.domain.Member;
import com.naver.myChat.bootstrap.service.IService;

@Controller
public class ChatController {
	
	@Autowired
	private IService memberService;
	
	@Value("${savefoldername}")
	private String saveFolder;
	
	private static final Logger logger = LoggerFactory.getLogger(ChatController.class); 
	
	@GetMapping("main")
	public String mainPage() {
		return "project/shop_main";
	}
	
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
	
	@ResponseBody
	@RequestMapping("/idcheck")
	public int idcheck(String id) {
		int result = memberService.isId(id);
		return result;
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
	
	
	
	@PostMapping("/joinProcess")
	public String joinProcess(Member member, RedirectAttributes rattr, Model model, HttpServletRequest request) throws Exception {
		
		MultipartFile uploadfile = member.getUploadfile();
		if(!uploadfile.isEmpty()) {
			String fileName = uploadfile.getOriginalFilename(); // 원래 파일명 
			member.setOriginalfile(fileName); // 원래 파일명 저장
		
		// 새로운 폴더 이름 : 오늘 년+월+일 
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);	// 오늘 년도 구합니다.
		int month = c.get(Calendar.MONTH)+1;  // 오늘 월 구합니다. 
		int date = c.get(Calendar.DATE);  // 오늘 일 구합니다. 
		String homedir = saveFolder + year + "-" + month + "-" + date;
		logger.info(homedir);
		File path1 = new File(homedir);
		if(!(path1.exists())) {
			path1.mkdir(); // 새로운 폴더 생성 
		}
		
		// 난수를 구합니다. 
		Random r= new Random();
		int random = r.nextInt(100000000);
		
		// 확장자 구하기 시작 
		int index = fileName.lastIndexOf(".");
		logger.info("index = " + index);
		
		String fileExtension = fileName.substring(index+1);
		logger.info("fileExtension = " + fileExtension);
		
		// 새로운 파일명 저장 
		String refileName = "bbs" + year + month + date + random + "." + fileExtension;
		logger.info("refileName = " + refileName);
		
		// 오라클 디비에 저장될 파일 명 
		String fileDBName = "/" + year + "-" + month + "-" + date + "/" + refileName;
		logger.info("fileDBName = " + fileDBName);
		
		// transferTo(file path) : 업로드한 파일을 매개변수의 경로에 저장 
		uploadfile.transferTo(new File(saveFolder + fileDBName));
		
		// 바뀐 파일명으로 저장 
		member.setSavefile(fileDBName);
		}
		
		int result = memberService.insert(member); // 저장메서드 호출
		// 삽입된 경우 
		if(result ==1) {
			rattr.addFlashAttribute("result", "joinSuccess");
			return "redirect:login";
		} else {
			model.addAttribute("url", request.getRequestURL());
			model.addAttribute("message", "회원가입 실패");
			return "error/error";
		}
	} 
	
	@PostMapping("/loginProcess")
	public String  loginProcess(String id, String password, HttpSession session, HttpServletRequest request, RedirectAttributes rattr, Model mv) throws Exception {
		Map<String, Object> map = memberService.isMember(id, password);
		int result = Integer.parseInt(map.get("result").toString());
		logger.info("result = " + result);
		
		if(result ==1) {
			// 이미 로그인 중에 다시 로그인을 하는 경우 
			
			session.setAttribute("id", id);
			mv.addAttribute("name", id);
			mv.addAttribute("filename", map.get("filename").toString());
			
			// mv.addObject("original", map.get("original").toString());
			
			// ip로 접근하는 경우와 localhost로 접근하는 경우 모두 적용하기 위해 
			// 접근할 url을 구합니다. 
			String requestURL = request.getRequestURL().toString();
			// http://localthost:9988.mychat/loginProcess
			logger.info(requestURL);
			int start = requestURL.indexOf("//");
			int end = requestURL.lastIndexOf("/");
			String url = requestURL.substring(start, end);
			
			logger.info("url = " + url);	// url=localthost:9988/myChat 
			mv.addAttribute("url", url);
			
			return "boot";
		} else {
			logger.info("로그인 문제");
			rattr.addFlashAttribute("result", result);
			return "redirect:login";
		}
	}
	
}
