package com.powerfuljava.service;

import java.util.List;

import com.powerfuljava.domain.Criteria;
import com.powerfuljava.domain.ReplyPageDTO;
import com.powerfuljava.domain.ReplyVO;

public interface ReplyService {
	
	//댓글 작성
	public int register(ReplyVO reply);	
	
	//댓글 읽기
	public ReplyVO get(Long rno);		
	
	//댓글 수정
	public int modify(ReplyVO reply);											
	
	//댓글 삭제
	public int remove(Long rno);
	
	//댓글 목록 - 게시물 읽기 페이지 내에서.. 
	public List<ReplyVO> getList(Criteria criteria, Long bno);
	
	//댓글 목록 - 페이징.. 
	public ReplyPageDTO getListPage(Criteria criteria, Long bno);
}
