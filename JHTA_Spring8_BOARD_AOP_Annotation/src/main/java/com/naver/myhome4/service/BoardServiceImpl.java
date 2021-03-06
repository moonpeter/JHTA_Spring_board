package com.naver.myhome4.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.naver.myhome4.dao.BoardDAO;
import com.naver.myhome4.domain.Board;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardDAO dao;

	@Override
	public int getListCount() {
		return dao.getListCount();
	}

	@Override
	public List<Board> getBoardList(int page, int limit) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		int startrow = (page - 1) * limit + 1;
		int endrow = startrow + limit - 1;
		map.put("start", startrow);
		map.put("end", endrow);
		return dao.getBoardList(map);
	}

	@Transactional
	public Board getDetail(int num) {
		if(setReadCountUpdate(num)!=1) {
			return null;
		}
		double i = 1/0; // rollback 되는지 확인 
		return dao.getDetail(num);
	}

	@Transactional
	public int boardReply(Board board) {
		boardReplyUpdate(board);
		board.setBoard_re_lev(board.getBoard_re_lev()+1);
		board.setBoard_re_seq(board.getBoard_re_seq()+1);
		return dao.boardReply(board);
	}
	
	@Override
	public int boardReplyUpdate(Board board) {
		return dao.boardReplyUpdate(board);
	}

	@Override
	public int boardModify(Board modifyboard) {
		// TODO Auto-generated method stub
		return dao.boardModify(modifyboard);
	}

	@Override
	public int boardDelete(int num) {
		int result=0;
		Board board = dao.getDetail(num);
		if(board != null) {
			//추가 - 삭제할 파일 목록들을 저장하기 위한 메서드 호출
			if(board.getBoard_file()!=null) {
				  dao.insertDeleteFiles(board.getBoard_file());	
			}
			result=dao.boardDelete(board);
		}
		return result;
	}

	@Override
	public int setReadCountUpdate(int num) {
		return dao.setReadCountUpdate(num);
	}

	@Override
	public boolean isBoardWriter(int num, String pass) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("num", num);
		map.put("pass", pass);
		Board result = dao.isBoardWriter(map);
		if(result == null) 
			return false;
		else
			return true;
	}

	@Override
	public void insertBoard(Board board) {
		dao.insertBoard(board);
	}

	@Override
	public List<String> getDeleteFileList() {
		return dao.getDelteFileList();
	}

	@Override
	public void insertDeleteFiles(String before_file) {
		dao.insertDeleteFiles(before_file);
	}

}
