package com.powerfuljava.service;

import java.util.List;


import com.powerfuljava.domain.GalleryPaging;
import com.powerfuljava.domain.TravellReplyPageDTO;
import com.powerfuljava.domain.TravellReplyVO;


public interface TravellReplyService {
	
	//댓글 작성
	public int register(TravellReplyVO travellReply);	
	
	//댓글 읽기
	public TravellReplyVO get(Long t_rno);		
	
	//댓글 수정
	public int modify(TravellReplyVO travellReply);											
	
	//댓글 삭제
	public int remove(Long t_rno);
	
	//댓글 목록 - 게시물 읽기 페이지 내에서.. 
	public List<TravellReplyVO> getList(GalleryPaging galleryPaging, Long t_bno);
	
	//댓글 목록 - 페이징.. 
	public TravellReplyPageDTO getListPage(GalleryPaging galleryPaging, Long t_bno);
}
