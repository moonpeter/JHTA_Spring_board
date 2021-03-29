package com.naver.myhome4.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.naver.myhome4.dao.MemberDAO;
import com.naver.myhome4.domain.Member;

public interface MemberService {

	public int isId(String id, String pass);

	public int insert(Member m);

	public int isId(String id);

	public Member member_info(String id);

	public void delete(String id);

	public int update(Member m);

	public List<Member> getSearchList(String index, String search_word, int page, int limit);

	public int getSearchListcount(String index, String search_word);
}
