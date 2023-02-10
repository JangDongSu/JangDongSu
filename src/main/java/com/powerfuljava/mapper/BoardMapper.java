package com.powerfuljava.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
//import org.apache.ibatis.annotations.Select;

import com.powerfuljava.domain.BoardVO;
import com.powerfuljava.domain.Criteria;

public interface BoardMapper {
	//@Select("select * from tbl_board where bno > 0")
	
	//게시판 목록
	public List<BoardVO> getList();
	
	//게시판 페이징
	public List<BoardVO> getListWithPaging(Criteria criteria);
	
	//게시물 읽기
	public BoardVO read(Long bno);
	
	//게시물 작성
	public void insert(BoardVO board);
	
	//게시물 수정
	public Integer update(BoardVO board);
	
	//게시물 삭제
	public Integer delete(Long bno);
	
	//게시물 조회수
	public int getViewCount(Long bno);
	
	//totalcount
	public int getTotalCount(Criteria criteria);
	
	public void updateReplyCnt(@Param("bno") Long bno,
			@Param("amount") int amount);
}
