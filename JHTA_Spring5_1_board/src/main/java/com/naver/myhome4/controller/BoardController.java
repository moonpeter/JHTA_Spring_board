package com.naver.myhome4.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.naver.myhome4.domain.Board;
import com.naver.myhome4.domain.Comment;
import com.naver.myhome4.service.BoardService;
import com.naver.myhome4.service.CommentService;

@Controller
@RequestMapping(value = "/board")
public class BoardController {

	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	@Autowired
	private BoardService boardService;

	@Autowired
	private CommentService commentService;
	
	@Value("${savefoldername}")
	private String saveFolder;

	@GetMapping(value = "/write")
	public String write() {
		return "board/board_write";
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView boardList(@RequestParam(value = "page", defaultValue = "1", required = false) int page,
			ModelAndView mv) {

		int limit = 10;
		int listcount = boardService.getListCount(); // ??? ????????? ?????? ?????????
		int maxpage = (listcount + limit - 1) / limit;
		int startpage = ((page - 1) / 10) * 10 + 1;
		int endpage = startpage + 10 - 1;
		if (endpage > maxpage) {
			endpage = maxpage;
		}

		List<Board> boardlist = boardService.getBoardList(page, limit);

		mv.setViewName("board/board_list");
		mv.addObject("page", page);
		mv.addObject("maxpage", maxpage);
		mv.addObject("startpage", startpage);
		mv.addObject("endpage", endpage);
		mv.addObject("listcount", listcount);
		mv.addObject("boardlist", boardlist);
		mv.addObject("limit", limit);
		return mv;
	}

	@ResponseBody
	@RequestMapping(value = "/list_ajax", method = RequestMethod.POST)
	public Map<String, Object> boardListAjax(
			@RequestParam(value = "page", defaultValue = "1", required = false) int page,
			@RequestParam(value = "limit", defaultValue = "10", required = false) int limit) {

		int listcount = boardService.getListCount(); // ??? ????????? ?????? ?????????
		int maxpage = (listcount + limit - 1) / limit;
		int startpage = ((page - 1) / 10) * 10 + 1;
		int endpage = startpage + 10 - 1;
		if (endpage > maxpage) {
			endpage = maxpage;
		}

		List<Board> boardlist = boardService.getBoardList(page, limit); // ???????????? ?????????

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("page", page);
		map.put("maxpage", maxpage);
		map.put("startpage", startpage);
		map.put("endpage", endpage);
		map.put("listcount", listcount);
		map.put("boardlist", boardlist);
		map.put("limit", limit);
		return map;
	}

	@PostMapping(value = "/add")
	public String add(Board board) throws Exception {

		MultipartFile uploadfile = board.getUploadfile();

		if (!uploadfile.isEmpty()) {
			String fileName = uploadfile.getOriginalFilename(); // ?????? ?????????
			board.setBoard_original(fileName); // ?????? ????????? ??????
//			String saveFolder = request.getSession().getServletContext().getRealPath("resources") + "/upload/";
			String fileDBName = fileDBName(fileName, saveFolder);
			logger.info("fileDBName = " + fileDBName);

			// transferTo(File path) : ???????????? ????????? ??????????????? ????????? ???????????????.
			uploadfile.transferTo(new File(saveFolder + fileDBName));

			// ?????? ??????????????? ??????
			board.setBoard_file(fileDBName);
		}

		boardService.insertBoard(board); // ??????????????? ??????

		return "redirect:list";
	}

	private String fileDBName(String fileName, String saveFolder) {

		// ????????? ?????? ?????? : ?????? + ??? + ??? + ???
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH)+1;
		int date = c.get(Calendar.DATE);

		String homedir = saveFolder + year + "-" + month + "-" + date;
		logger.info(homedir);
		File path1 = new File(homedir);
		if (!(path1.exists())) {
			path1.mkdir(); // ????????? ????????? ??????
		}

		// ????????? ????????????.
		Random r = new Random();
		int random = r.nextInt(100000000);

		// ????????? ????????? ??????
		int index = fileName.lastIndexOf(".");
		// ??????????????? ?????? ???????????? ?????????(index)??? ??????
		// indexOf??? ?????? ???????????? ???????????? ?????? index??? ???????????? ??????,
		// lastIndexOf??? ??????????????? ???????????? ???????????? index??? ???????????????.
		// (???????????? ?????? ????????? ?????? ?????? ??? ???????????? ???????????? ???????????? ????????? ???????????????.)
		logger.info("index = " + index);
		String fileExtension = fileName.substring(index + 1);
		logger.info("fileExtension = " + fileExtension);
		// ????????? ????????? ???

		// ????????? ?????????
		String refileName = "bbs" + year + month + date + random + "." + fileExtension;
		logger.info("refileName = " + refileName);

		// ????????? ????????? ????????? ?????????
		String fileDBName = "/" + year + "-" + month + "-" + date + "/" + refileName;
		logger.info("fileDBName = " + fileDBName);

		return fileDBName;
	}

	@GetMapping("/detail")
	public ModelAndView detail(int num, ModelAndView mv, HttpServletRequest request) {
		Board board = boardService.getDetail(num);
		if (board == null) {
			logger.info("???????????? ??????");
			mv.setViewName("error/error");
			mv.addObject("url", request.getRequestURL());
			mv.addObject("message", "???????????? ???????????????.");
		} else {
			logger.info("???????????? ??????");
			int count = commentService.getListCount(num);
			mv.setViewName("board/board_view");
			mv.addObject("count", count);
			mv.addObject("boarddata", board);
		}
		return mv;
	}

	@GetMapping("/modifyView")
	public ModelAndView modifyView(int num, ModelAndView mv, HttpServletRequest request) {
		Board boarddata = boardService.getDetail(num);

		if (boarddata == null) {
			logger.info("???????????? ??????");
			mv.setViewName("error/error");
			mv.addObject("url", request.getRequestURL());
			mv.addObject("message", "???????????? ???????????????.");
			return mv;
		}
		logger.info("(??????) ???????????? ??????");
		// ?????? ??? ???????????? ????????? ??? ?????? ??? ????????? ???????????? ????????? boarddata ?????????
		// ModelAndView ????????? ???????????????.
		mv.addObject("boarddata", boarddata);
		// ??? ?????? ??? ???????????? ???????????? ?????? ????????? ???????????????.
		mv.setViewName("board/board_modify");
		return mv;
	}

	@PostMapping("/modifyAction")
	public String BoardModifyAction(Board boarddata, String check, Model mv, HttpServletRequest request,
			RedirectAttributes rattr) throws Exception {
		
		// <input type="hidden" name="BOARD_FILE" value="${baorddata.BOARD_FILE}">
		// baorddata.getBoard_file()??? ??? ????????? ?????? ???????????????. ???, ????????? ????????? ?????? ???????????????. 
		String before_file = boarddata.getBoard_file();
		
		boolean usercheck = boardService.isBoardWriter(boarddata.getBoard_num(), boarddata.getBoard_pass());
		String url = "";

		// ??????????????? ????????????
		if (usercheck == false) {
			rattr.addFlashAttribute("result", "passFail");
			rattr.addAttribute("num", boarddata.getBoard_num());
			return "redirect:modifyView";
		}

		MultipartFile uploadfile = boarddata.getUploadfile();
//		String saveFolder = request.getSession().getServletContext().getRealPath("resources") + "/upload/";

		if (check != null && !check.equals("")) { // ?????? ?????? ????????? ???????????? ??????
			logger.info("???????????? ????????? ???????????????.");
			boarddata.setBoard_original(check);

		} else {
			if (uploadfile!=null && !uploadfile.isEmpty()) {
				logger.info("?????? ?????????????????????.");

				String fileName = uploadfile.getOriginalFilename(); // ?????? ????????? 
				
				boarddata.setBoard_original(fileName);

				String fileDBName = fileDBName(fileName, saveFolder);

				// transferTo(File path) : ???????????? ????????? ??????????????? ????????? ???????????????.
				uploadfile.transferTo(new File(saveFolder + fileDBName));

				// ?????? ??????????????? ??????
				boarddata.setBoard_file(fileDBName);
			} else { // uploadfile.isEmpty() ??? ?????? - ????????? ???????????? ?????? ???
				logger.info("?????? ?????? ????????????.");
				boarddata.setBoard_file(""); // ""??? ????????? ?????????.
				boarddata.setBoard_original("");// ""??? ????????? ?????????.
			}
		}

		// DAO?????? ?????? ????????? ???????????? ???????????????.
		int result = boardService.boardModify(boarddata);
		// ????????? ????????? ??????
		if (result == 0) {
			logger.info("????????? ?????? ??????");
			mv.addAttribute("url", request.getRequestURL());
			mv.addAttribute("message", "????????? ?????? ??????");
			url = "error/error";
		} else { // ?????? ??????
			logger.info("????????? ?????? ??????");
			// ????????? ??? ????????? ???????????? ?????? ??? ?????? ?????? ???????????? ???????????? ?????? ????????? ??????
			url = "redirect:detail";
			rattr.addAttribute("num", boarddata.getBoard_num());
			
			// ?????? ????????? ?????? ????????? ?????? 
			// ?????? ?????? ????????? ?????? ?????? ???????????? ?????? ???????????? ?????? ?????? ????????? ????????? ???????????? ?????? 
			if(!before_file.equals("") && !before_file.equals(boarddata.getBoard_file())) {
				// ?????? ???????????? DB??? ?????? ????????? ??????????????? ?????? 
				boardService.insertDeleteFiles(before_file);
			}
		}
		return url;
	}

	@GetMapping(value = "/replyView")
	public ModelAndView replyView(int num, ModelAndView mv, HttpServletRequest request) {
		Board boarddata = boardService.getDetail(num);
		if (boarddata == null) {
			mv.setViewName("error/error");
			mv.addObject("url", request.getRequestURL());
			mv.addObject("message", "????????? ????????? ???????????? ??????");
		} else {
			mv.addObject("boarddata", boarddata);
			mv.setViewName("board/board_reply");
		}
		return mv;
	}
	
	@PostMapping("/replyAction")
	public ModelAndView replyAction(Board board, ModelAndView mv, HttpServletRequest request) {
		int result = boardService.boardReply(board);
		if(result == 0) {
			mv.setViewName("error/error");
			mv.addObject("url", request.getRequestURL());
			mv.addObject("message", "????????? ?????? ?????? ??????");
		} else {
			mv.setViewName("redirect:list");
		}
		return mv;
	}
	
	@PostMapping("/delete")
	public String boardDeleteAction(String board_pass, int num, Model mv, RedirectAttributes rattr, HttpServletRequest request) throws Exception {
		boolean usercheck = boardService.isBoardWriter(num, board_pass);
		
		// ???????????? ???????????? ?????? ??????
		if(usercheck == false) {
			rattr.addFlashAttribute("result", "passFail");
			rattr.addAttribute("num", num);
			return "redirect:detail";
		}
		
		// ???????????? ???????????? ?????? ?????? ??????
		int result = boardService.boardDelete(num);
		
		// ?????? ?????? ????????? ??????
		if(result == 0) {
			logger.info("????????? ?????? ??????");
			mv.addAttribute("url", request.getRequestURL());
			mv.addAttribute("message", "?????? ??????");
			return "error/error";
		}
		
		// ?????? ?????? ????????? ?????? - ??? ?????? ?????? ????????? ???????????? ??????
		logger.info("????????? ?????? ??????");
		rattr.addFlashAttribute("result", "deleteSuccess");
		return "redirect:list";
	}
	
	@GetMapping(value="/down", produces=MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<Resource> boardFileDown(String original, String filename, HttpServletRequest request) {
		// ????????? ???????????? ?????? ????????? ???????????? ?????? 
//		String saveFolder = request.getSession().getServletContext().getRealPath("resources") + "/upload/";
		logger.info(saveFolder);
		// ????????? ????????? ?????? ????????? ???????????? ??????????????? ????????????
		Resource resource = new FileSystemResource(saveFolder + filename);
	
		// ????????? ?????? ?????? "NOT FOUND"??? ?????? 
		if (resource.exists() == false) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		HttpHeaders headers = new HttpHeaders();
		
		try {
			String downloadName = new String(original.getBytes("UTF-8"), "ISO-8859-1");
			headers.add("Content-Disposition", "attachment; filename=" + downloadName);
		} catch(UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);
	}
}
