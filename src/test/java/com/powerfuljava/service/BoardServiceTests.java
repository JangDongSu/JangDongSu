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
	
	//�Խù� ���
	@Test
	public void testRegister() {
		BoardVO board = new BoardVO();
		board.setTitle("title");
		board.setContent("content");
		board.setWriter("writer");
		service.register(board);
		log.info(service);
	}
	
	/*//�Խù� �ҷ�����
	@Test
	public void testGetList() {
		List<BoardVO> list = service.getList(new Criteria(3, 10));
		for(int i = 0; i<list.size(); i++) {
			log.info(list.get(i));
		}
	}*/
	
	/*//�Խù� ����
	@Test
	public void testUpdate() {
		BoardVO board = service.get(8L);	// 8L�� �Խù� ��ȣ
		if(board == null) { 
			return;
		}
		board.setTitle("������ �ɵ��� ������");
		board.setContent("������ ���� ���ϼ� ����... ");
		board.setWriter("������");
		log.info("Modify result : " + service.modify(board));
	}*/
	
	//�Խù� ����
	/*@Test
	public void testDelete() {
		log.info("delete : " + service.remove(35L));	// 11L�� �Խù� ��ȣ
	}*/
}
