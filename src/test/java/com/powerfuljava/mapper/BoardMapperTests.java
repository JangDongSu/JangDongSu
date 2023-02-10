package com.powerfuljava.mapper;

import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.powerfuljava.domain.BoardVO;
import com.powerfuljava.domain.Criteria;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@	Log4j
public class BoardMapperTests {
	@Autowired
	private BoardMapper mapper;
	
	@Test
	public void testGetList() {
		List<BoardVO> list = mapper.getList();
		log.info("getlist......");
		for(int i=0; i<list.size(); i++) {
			log.info(list.get(i));
		}
	}
	
	/*// -- 새 레코드 추가하기(쿼리작성 - 게시물 작성)
	@Test
	public void testIntert() {
		BoardVO board =  new BoardVO();
		board.setTitle("나온나!!!!");
		board.setContent("신문이요!!!");
		board.setWriter("마레이");
		mapper.insert(board);
		log.info(board);
	}*/
	
	/*// -- 기존 레코드 업데이트(게시물 수정)
	@Test
	public void testUpdate() {
		BoardVO board =  new BoardVO();
		board.setBno(7L);
		board.setTitle("럴커");
		board.setContent("ㅋㅋㅋㅋㅋㅋ");
		board.setWriter("배명환");
		
		int res = mapper.update(board);
		log.info("update count : " + board);
	}*/
	
	/*// -- 기존 레코드 삭제(게시물 삭제)
		@Test
		public void testDelete() {
			int count = mapper.delete(1L);
			log.info("delete count : " + count);
		}*/
		
	/*// -- 기존 레코드 read
	@Test
	public void testRead() {
		BoardVO board = mapper.read(1L);
		log.info(board);
	}
	@Test
	public void testPaging() {
		Criteria criteria = new Criteria();
		criteria.setPageNum(1);
		criteria.setAmount(10);
		criteria.calOffset();
		List<BoardVO> list = mapper.getListWithPaging(criteria);
		for(int i=0; i<list.size(); i++) {
			log.info(list.get(i));
		}	
	}*/
	
	// -- 게시물 키워드검색
	/*@Test
	public void testSearch() {
		Criteria criteria = new Criteria();
		criteria.setKeyword("씨발");
		criteria.setType("TCW");	// - T : title.....	C : content.....	W : writer
		criteria.calOffset();
		List<BoardVO> list = mapper.getListWithPaging(criteria);
		for(int i=0; i<list.size(); i++) {
			log.info(list.get(i));
		}	
	}*/
}
