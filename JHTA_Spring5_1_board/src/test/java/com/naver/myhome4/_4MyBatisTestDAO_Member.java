package com.naver.myhome4;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.naver.myhome4.domain.Board;
import com.naver.myhome4.domain.Member;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml" })
public class _4MyBatisTestDAO_Member {

	private static final Logger logger = LoggerFactory.getLogger(_4MyBatisTestDAO_Member.class);

	@Autowired
	private SqlSessionTemplate sqlsession;
	
//	@Test
	public void update() {
		Member member = new Member();
		member.setAge("21");
		member.setEmail("test@mail.net");
		member.setGender("여");
		member.setId("admin");
		member.setName("김길동");
		int count = sqlsession.update("Members.update", member);
		logger.info("------------" + count + " 개 update ====-----");
		
		Member result = sqlsession.selectOne("Members.idcheck", "admin");
		logger.info("이름 : " + result.getName());
		logger.info("이메일 : " + result.getEmail());
		logger.info("나이 : " + result.getAge());
		logger.info("성별 : " + result.getGender());
		logger.info("아이디 : " + result.getId());
		logger.info("비밀번호 : " + result.getPassword());
		logger.info("========================");
	}
	
	@Test
	public void search() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("search_field", "id");
		map.put("search_word", "%Test%");
		int count = sqlsession.selectOne("Members.searchCount", map);
		logger.info("============" + count + " 개 검색 =============");
		
		map.put("start", 1);
		map.put("end", 3);
		List<Member> list = sqlsession.selectList("Members.getSearchList", map);
		for (Member result : list) {
			logger.info("이름 : " + result.getName());
			logger.info("이메일 : " + result.getEmail());
			logger.info("나이 : " + result.getAge());
			logger.info("성별 : " + result.getGender());
			logger.info("아이디 : " + result.getId());
			logger.info("비밀번호 : " + result.getPassword());
			logger.info("========================");
		}
	}
}
