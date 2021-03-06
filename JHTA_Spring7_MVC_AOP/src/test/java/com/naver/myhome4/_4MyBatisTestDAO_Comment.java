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
import com.naver.myhome4.domain.Comment;
import com.naver.myhome4.domain.Member2;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml" })
public class _4MyBatisTestDAO_Comment {

	private static final Logger logger = LoggerFactory.getLogger(_4MyBatisTestDAO_Comment.class);

	@Autowired
	private SqlSessionTemplate sqlsession;
	
//	@Test
	public void count() {
		int board_num=12;
		int result = sqlsession.selectOne("Comments.count", board_num);
		logger.info("===== " + board_num + "번 게시글에 " + result + "개의 댓글이 있습니다. =====");
	}
	
//	@Test
	public void insert() {
		int board_num=12;
		Comment c = new Comment();
		c.setBoard_num(board_num);
		c.setContent("jUnit 에서 보내요33");
		c.setId("admin");
		int result = sqlsession.insert("Comments.insert", c);
		logger.info("삽입한 갯수 : " + result);
	}
	
//	@Test
	public void list() {
		int board_num=12;
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("board_num", board_num);
		map.put("start", 1);
		map.put("end", 3);
		List<Comment> list = sqlsession.selectList("Comments.getList", map);
		for(Comment comment : list) {
			logger.info("작성자 : " + comment.getId());
			logger.info("내용 : " + comment.getContent());
			logger.info("날짜 : " + comment.getReg_date());
			logger.info("===============");
		}
	}
	
	@Test
	public void update() {
		Comment c = new Comment();
		c.setBoard_num(12);
		c.setNum(15);
		c.setContent("제이유닛에서 댓글 수정");
		sqlsession.update("Comments.update", c);
	}
}
