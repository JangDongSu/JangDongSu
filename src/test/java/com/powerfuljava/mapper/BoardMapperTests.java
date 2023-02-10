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
	
	/*// -- 歯 傾坪球 蓄亜馬奄(汀軒拙失 - 惟獣弘 拙失)
	@Test
	public void testIntert() {
		BoardVO board =  new BoardVO();
		board.setTitle("蟹紳蟹!!!!");
		board.setContent("重庚戚推!!!");
		board.setWriter("原傾戚");
		mapper.insert(board);
		log.info(board);
	}*/
	
	/*// -- 奄糎 傾坪球 穣汽戚闘(惟獣弘 呪舛)
	@Test
	public void testUpdate() {
		BoardVO board =  new BoardVO();
		board.setBno(7L);
		board.setTitle("群朕");
		board.setContent("せせせせせせ");
		board.setWriter("壕誤発");
		
		int res = mapper.update(board);
		log.info("update count : " + board);
	}*/
	
	/*// -- 奄糎 傾坪球 肢薦(惟獣弘 肢薦)
		@Test
		public void testDelete() {
			int count = mapper.delete(1L);
			log.info("delete count : " + count);
		}*/
		
	/*// -- 奄糎 傾坪球 read
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
	
	// -- 惟獣弘 徹趨球伊事
	/*@Test
	public void testSearch() {
		Criteria criteria = new Criteria();
		criteria.setKeyword("松降");
		criteria.setType("TCW");	// - T : title.....	C : content.....	W : writer
		criteria.calOffset();
		List<BoardVO> list = mapper.getListWithPaging(criteria);
		for(int i=0; i<list.size(); i++) {
			log.info(list.get(i));
		}	
	}*/
}
