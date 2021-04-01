package com.naver.myChat.bootstrap.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.naver.myChat.bootstrap.dao.MemberDAO;
import com.naver.myChat.bootstrap.domain.Member;

@Service
public class MemberService implements IService{
	
	@Autowired
	private MemberDAO dao;
	
	@Transactional
	public int insert(Member member) {
		return dao.insert(member);
	}

	@Override
	public Map<String, Object> isMember(String id, String password) {
		Map<String, Object> map = new HashMap<String, Object>();
		Member member = dao.isMember(id);
		int result = -1; 	// null 인 경우 - 아이디가 존재하지 않는 경우 
		if(member != null) { // 아이디가 존재하는 경우
			if (member.getPassword().equals(password)) {
				result = 1;	// 아이디와 비번 같은 경우 
				map.put("filename", member.getSavefile());
			} else {
				result = 0; 	// 아이디는 같고 비번이 다른 경우
			}
		}
		map.put("result", result);
		return map;
	}
	
	@Override
	public int isId(String id) {
		Member member = dao.isMember(id);
		int result = -1;
		if (member != null) {
			result =0;
		}
		return result;
	}

}
