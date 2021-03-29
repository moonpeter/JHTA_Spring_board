package com.naver.myhome4.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.naver.myhome4.domain.Board;
import com.naver.myhome4.domain.Comment;
import com.naver.myhome4.service.CommentService;

@Controller
@RequestMapping(value = "/comment")
public class CommentController {

	private static final Logger logger = LoggerFactory.getLogger(CommentController.class);

	@Autowired
	private CommentService commentService;

	@ResponseBody
	@RequestMapping(value="/list")
	public Map<String, Object> list(int board_num, int page) {
		List<Comment> list = commentService.getCommentList(board_num, page); // 리스트를 받아옴
		int listcount = commentService.getListCount(board_num);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		map.put("listcount", listcount);
		return map;
	}
	
	@PostMapping("/add")
	public void commentAdd(Comment co, HttpServletResponse response) throws Exception {
		int ok = commentService.commentsInsert(co);
		response.getWriter().print(ok);
	}
	
	@PostMapping("/update")
	public void commentUpdate(Comment co, HttpServletResponse response) throws Exception {
		int ok = commentService.commentsUpdate(co);
		response.getWriter().print(ok);
	}
	
	@PostMapping("/delete")
	public void commentDelete(int num, HttpServletResponse response) throws Exception {
		int ok = commentService.commentsDelete(num);
		response.getWriter().print(ok);
	}
	
}
