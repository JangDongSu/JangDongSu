package com.powerfuljava.mapper;

import java.util.List;

import com.powerfuljava.domain.Criteria;
//import com.powerfuljava.domain.LatestCriteria;

//import org.apache.ibatis.annotations.Select;

import com.powerfuljava.domain.NoticeVO;

public interface NoticeMapper {
	//@Select("select * from notice_board where n_bno > 0")
	public List<NoticeVO> getList();
	
	//게시판 페이징
	public List<NoticeVO> getListWithPaging(Criteria criteria);
	
	//게시물 읽기
	public NoticeVO read(Long n_bno);
		
	//게시물 작성
	public void insert(NoticeVO notice);
		
	//게시물 수정
	public Integer update(NoticeVO notice);
		
	//게시물 삭제
	public Integer delete(Long n_bno);
	
	public int getTotalCount(Criteria criteria);
	
	//최신글
	public List<NoticeVO> getLatestList(Criteria criteria);
	
	public int getViewCount(Long n_bno);
}
