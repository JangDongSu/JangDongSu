package com.powerfuljava.service;

import java.util.List;

import com.powerfuljava.domain.Criteria;
//import com.powerfuljava.domain.LatestCriteria;
import com.powerfuljava.domain.NoticeAttachVO;
import com.powerfuljava.domain.NoticeVO;

public interface NoticeService {
	
	public void register(NoticeVO notice);
	
	public NoticeVO get(Long n_bno);
	
	public boolean modify(NoticeVO notice);	
	
	public boolean remove(Long n_bno);
	
	public List<NoticeVO> getList(Criteria criteria);
	
	public int getTotalCount(Criteria criteria);
	
	//게시물 조회수
	public int getViewCount(Long n_bno);
	
	public List<NoticeVO> getLatestList(Criteria criteria);
	
	public List<NoticeAttachVO> getAttachList(Long n_bno);
}
