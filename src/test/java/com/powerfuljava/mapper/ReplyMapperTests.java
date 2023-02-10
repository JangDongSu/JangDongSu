package com.powerfuljava.mapper;

import java.util.List;
import java.util.stream.IntStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.powerfuljava.domain.Criteria;
import com.powerfuljava.domain.ReplyVO;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ReplyMapperTests {
	@Autowired
	private ReplyMapper mapper;
	private Long[] bnoArr = {47L, 46L, 45L/*, 41L, 40L*/};
	
	/*@Test
	public void testMapper() {
		log.info(mapper);
	}*/
	
	/*// -- 새 레코드 추가하기(쿼리작성 - 댓글 작성)
	@Test
	public void testCreate() {
		IntStream.rangeClosed(1, 10).forEach(i -> {
		ReplyVO reply =  new ReplyVO();
		reply.setBno(bnoArr[i % 5]);
		reply.setReply("reply : " + i);
		reply.setReplyer("replyer : " + i);
		mapper.insert(reply);
		});
	}*/
	
	/*// -- 기존 레코드 업데이트(댓글 수정)
	@Test
	public void testUpdate() {
		ReplyVO reply =  mapper.read(46L);
		reply.setReply("ㅉㅉㅉㅉㅉㅉ");
		//reply.setReplyer("ㅇㅇ");
		
		int count = mapper.update(reply);
		log.info("update count" + count);
	}*/
	
	/*// -- 기존 레코드 삭제(댓글 삭제)
	@Test
	public void testDelete() {
		int count = mapper.delete(16L);
		log.info("delete count : " + count);
	}*/
	
	/*// -- 기존 댓글 read
	@Test
	public void testRead() {
		Long rno = 18L;
		ReplyVO reply = mapper.read(rno);
		log.info(reply);
	}*/
	
	/*// -- 게시물 읽기페이지에 댓글 목록 표시 
	@Test
	public void testList() {
		Criteria criteria = new Criteria();
		List<ReplyVO> list = mapper.getListWithPaging(criteria, 43L);
		list.forEach(reply->log.info(reply));
	}*/
	
	// -- 댓글 페이징
	@Test
	public void testPaging() {
		Criteria criteria = new Criteria();
		criteria.setPageNum(1);
		criteria.setAmount(10);
		criteria.calOffset();
		List<ReplyVO> list = mapper.getListWithPaging(criteria, 43L);
		for(int i=0; i<list.size(); i++) {
			log.info(list.get(i));
		}
	}
}
