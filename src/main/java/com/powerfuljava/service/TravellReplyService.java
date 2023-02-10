package com.powerfuljava.service;

import java.util.List;


import com.powerfuljava.domain.GalleryPaging;
import com.powerfuljava.domain.TravellReplyPageDTO;
import com.powerfuljava.domain.TravellReplyVO;


public interface TravellReplyService {
	
	//��� �ۼ�
	public int register(TravellReplyVO travellReply);	
	
	//��� �б�
	public TravellReplyVO get(Long t_rno);		
	
	//��� ����
	public int modify(TravellReplyVO travellReply);											
	
	//��� ����
	public int remove(Long t_rno);
	
	//��� ��� - �Խù� �б� ������ ������.. 
	public List<TravellReplyVO> getList(GalleryPaging galleryPaging, Long t_bno);
	
	//��� ��� - ����¡.. 
	public TravellReplyPageDTO getListPage(GalleryPaging galleryPaging, Long t_bno);
}
