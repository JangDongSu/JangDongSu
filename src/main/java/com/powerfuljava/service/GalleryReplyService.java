package com.powerfuljava.service;

import java.util.List;

import com.powerfuljava.domain.GalleryReplyVO;
import com.powerfuljava.domain.GalleryPaging;
import com.powerfuljava.domain.GalleryReplyPageDTO;

public interface GalleryReplyService {
	
	//댓글 작성
	public int register(GalleryReplyVO galleryReply);	
	
	//댓글 읽기
	public GalleryReplyVO get(Long p_rno);		
	
	//댓글 수정
	public int modify(GalleryReplyVO galleryReply);											
	
	//댓글 삭제
	public int remove(Long p_rno);
	
	//댓글 목록 - 게시물 읽기 페이지 내에서.. 
	public List<GalleryReplyVO> getList(GalleryPaging galleryPaging, Long p_bno);
	
	//댓글 목록 - 페이징.. 
	public GalleryReplyPageDTO getListPage(GalleryPaging galleryPaging, Long p_bno);
}
