package com.naver.myhome4.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.naver.myhome4.domain.Member2;

@Repository
public class MemberDAO {

	@Autowired
	private SqlSessionTemplate sqlSession;

	public Member2 isId(String id) {
		return sqlSession.selectOne("Members.idcheck", id);
	}

	public int insert(Member2 m) {
		return sqlSession.insert("Members.insert", m);
	}

	public Member2 memberInfo(String id) {
		return sqlSession.selectOne("Members.idcheck", id);
	}

	public int update(Member2 m) {
		return sqlSession.update("Members.update", m);
	}

	public int getSearchListCount(Map<String, Object> map) {
		return sqlSession.selectOne("Members.searchCount", map);
	}
	
	public List<Member2> getSearchList(Map<String, Object> map) {
		return sqlSession.selectList("Members.getSearchList", map);
	}

	public void delete(String id) {
		sqlSession.delete("Members.delete", id);
	}
}
