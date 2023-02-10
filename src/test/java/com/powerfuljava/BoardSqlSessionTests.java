package com.powerfuljava;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.powerfuljava.domain.BoardVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class BoardSqlSessionTests {
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Test
	public void testInsertBoardWithSqlSessionFactory() {
		SqlSession session = sqlSessionFactory.openSession();
		BoardVO board = new BoardVO();
		board.setTitle("货臂");
		board.setContent("货臂....sqlSessionFactory");
		board.setWriter("writer1234");
		session.insert("com.powerfuljava.mapper.BoardMapper.insert", board);
	}
	
	@Test
	public void testInsertBoardWithSqlSession() {
		BoardVO board = new BoardVO();
		board.setTitle("货臂");
		board.setContent("货臂....sqlSessionFactory");
		board.setWriter("writer1234");
		sqlSessionTemplate.insert("com.powerfuljava.mapper.BoardMapper.insert", board);
	}
}
