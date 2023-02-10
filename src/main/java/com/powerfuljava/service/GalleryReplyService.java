package com.powerfuljava.service;

import java.util.List;

import com.powerfuljava.domain.GalleryReplyVO;
import com.powerfuljava.domain.GalleryPaging;
import com.powerfuljava.domain.GalleryReplyPageDTO;

public interface GalleryReplyService {
	
	//��� �ۼ�
	public int register(GalleryReplyVO galleryReply);	
	
	//��� �б�
	public GalleryReplyVO get(Long p_rno);		
	
	//��� ����
	public int modify(GalleryReplyVO galleryReply);											
	
	//��� ����
	public int remove(Long p_rno);
	
	//��� ��� - �Խù� �б� ������ ������.. 
	public List<GalleryReplyVO> getList(GalleryPaging galleryPaging, Long p_bno);
	
	//��� ��� - ����¡.. 
	public GalleryReplyPageDTO getListPage(GalleryPaging galleryPaging, Long p_bno);
}
