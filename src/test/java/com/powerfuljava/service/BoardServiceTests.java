package com.powerfuljava.service;

import static org.junit.Assert.assertNotNull;

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
@Log4j
public class BoardServiceTests {
	@Autowired
	private BoardService service;
	
	/*@Test
	public void testExist() {
		log.info(service);
		assertNotNull(service);
	}*/
	
	//게시물 등록
	@Test
	public void testRegister() {
		BoardVO board = new BoardVO();
		board.setTitle("title");
		board.setContent("content");
		board.setWriter("writer");
		service.register(board);
		log.info(service);
	}
	
	/*//게시물 불러오기
	@Test
	public void testGetList() {
		List<BoardVO> list = service.getList(new Criteria(3, 10));
		for(int i = 0; i<list.size(); i++) {
			log.info(list.get(i));
		}
	}*/
	
	/*//게시물 수정
	@Test
	public void testUpdate() {
		BoardVO board = service.get(8L);	// 8L은 게시물 번호
		if(board == null) { 
			return;
		}
		board.setTitle("위대한 령도자 김정은");
		board.setContent("위대한 수령 김일성 동지... ");
		board.setWriter("김정은");
		log.info("Modify result : " + service.modify(board));
	}*/
	
	//게시물 삭제
	/*@Test
	public void testDelete() {
		log.info("delete : " + service.remove(35L));	// 11L은 게시물 번호
	}*/
}
