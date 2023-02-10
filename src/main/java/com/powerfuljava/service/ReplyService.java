package com.powerfuljava.service;

import java.util.List;

import com.powerfuljava.domain.Criteria;
import com.powerfuljava.domain.ReplyPageDTO;
import com.powerfuljava.domain.ReplyVO;

public interface ReplyService {
	
	//��� �ۼ�
	public int register(ReplyVO reply);	
	
	//��� �б�
	public ReplyVO get(Long rno);		
	
	//��� ����
	public int modify(ReplyVO reply);											
	
	//��� ����
	public int remove(Long rno);
	
	//��� ��� - �Խù� �б� ������ ������.. 
	public List<ReplyVO> getList(Criteria criteria, Long bno);
	
	//��� ��� - ����¡.. 
	public ReplyPageDTO getListPage(Criteria criteria, Long bno);
}
