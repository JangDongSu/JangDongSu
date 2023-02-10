package com.powerfuljava.service;

import java.util.List;

import com.powerfuljava.domain.BoardAttachVO;
import com.powerfuljava.domain.BoardVO;
import com.powerfuljava.domain.Criteria;

public interface BoardService {
	
	public void register(BoardVO board);
	
	public BoardVO get(Long bno);
	
	public boolean modify(BoardVO board);	
	
	public boolean remove(Long bno);
	
	public List<BoardVO> getList(Criteria criteria);
	
	public int getTotalCount(Criteria criteria);
	
	public List<BoardAttachVO> getAttachList(Long bno);
	
	//게시물 조회수
	public int getViewCount(Long bno);
}
