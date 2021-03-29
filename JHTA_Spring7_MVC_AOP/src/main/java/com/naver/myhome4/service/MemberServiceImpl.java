package com.naver.myhome4.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.naver.myhome4.dao.MemberDAO;
import com.naver.myhome4.domain.Board;
import com.naver.myhome4.domain.Member2;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberDAO dao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public int isId(String id, String password) {
		Member2 rmember = dao.isId(id);
		int result = -1; // 아이디가 존재하지 않는 경우- rmember가 null인 경우
		if (rmember != null) { // 아이디가 존재하는 경우
			// paosswordEncoder.matches(rawPassword, encodedPassword) 
			// 사용자에게 입력받은 패스워드를 비교하고자 할 때 사용하는 메서드입니다. 
			// rawPassword : 사용자가 입력한 패스워드 
			// encodedPassword : DB에 저장된 패스워드 
			if (passwordEncoder.matches(password, rmember.getPassword())) {
				result = 1; // 아이디와 비밀번호가 일치하는 경우
			} else {
				result = 0; // 아이디는 존재하지만 비밀번호가 일치하지 않는 경우
			}
		}
		return result;
	}

	@Override
	public int insert(Member2 m) {
		return dao.insert(m);
	}

	@Override
	public int isId(String id) {
		Member2 rmember = dao.isId(id);
		return (rmember == null) ? -1 : 1;
	}

	@Override
	public Member2 member_info(String id) {
		return dao.memberInfo(id);
	}

	@Override
	public void delete(String id) {
		dao.delete(id);
	}

	@Override
	public int update(Member2 m) {
		return dao.update(m);
	}

	@Override
	public List<Member2> getSearchList(String index, String search_word, int page, int limit) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(!index.equals("")) {
			String[] search_field = index.split("");
			map.put("search_field", search_field);
			map.put("search_word", "%" + search_word + "%");
		}
		int startrow = (page-1)*limit+1;
		int endrow = startrow + limit -1;
		map.put("start",  startrow);
		map.put("end",  endrow);
		return dao.getSearchList(map);
	}

	@Override
	public int getSearchListcount(String index, String search_word) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(!index.equals("")) {
			String[] search_field = index.split("");
			map.put("search_field", search_field);
			map.put("search_word", "%" + search_word + "%");
		}
		return dao.getSearchListCount(map);
	}
}
