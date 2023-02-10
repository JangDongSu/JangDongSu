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
	
	/*// -- �� ���ڵ� �߰��ϱ�(�����ۼ� - �Խù� �ۼ�)
	@Test
	public void testIntert() {
		BoardVO board =  new BoardVO();
		board.setTitle("���³�!!!!");
		board.setContent("�Ź��̿�!!!");
		board.setWriter("������");
		mapper.insert(board);
		log.info(board);
	}*/
	
	/*// -- ���� ���ڵ� ������Ʈ(�Խù� ����)
	@Test
	public void testUpdate() {
		BoardVO board =  new BoardVO();
		board.setBno(7L);
		board.setTitle("��Ŀ");
		board.setContent("������������");
		board.setWriter("���ȯ");
		
		int res = mapper.update(board);
		log.info("update count : " + board);
	}*/
	
	/*// -- ���� ���ڵ� ����(�Խù� ����)
		@Test
		public void testDelete() {
			int count = mapper.delete(1L);
			log.info("delete count : " + count);
		}*/
		
	/*// -- ���� ���ڵ� read
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
	
	// -- �Խù� Ű����˻�
	/*@Test
	public void testSearch() {
		Criteria criteria = new Criteria();
		criteria.setKeyword("����");
		criteria.setType("TCW");	// - T : title.....	C : content.....	W : writer
		criteria.calOffset();
		List<BoardVO> list = mapper.getListWithPaging(criteria);
		for(int i=0; i<list.size(); i++) {
			log.info(list.get(i));
		}	
	}*/
}
