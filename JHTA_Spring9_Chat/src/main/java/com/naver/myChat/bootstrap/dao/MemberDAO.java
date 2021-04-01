package com.naver.myChat.bootstrap.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.naver.myChat.bootstrap.domain.Member;

@Repository
public class MemberDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	public int insert(Member member) {
		return sqlSession.insert("insert", member);
	}
	
	public Member isMember(String id) {
		return sqlSession.selectOne("idcheck", id);
	}

}
