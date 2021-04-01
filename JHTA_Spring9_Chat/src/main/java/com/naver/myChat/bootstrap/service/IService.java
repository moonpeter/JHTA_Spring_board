package com.naver.myChat.bootstrap.service;

import java.util.Map;

import com.naver.myChat.bootstrap.domain.Member;

public interface IService {
	public int isId(String id);
	public Map<String, Object> isMember(String id, String password);
	public int insert(Member member);
}
