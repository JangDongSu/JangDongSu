package com.powerfuljava.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
//import org.apache.ibatis.annotations.Select;

import com.powerfuljava.domain.GalleryPaging;
import com.powerfuljava.domain.TravellVO;

public interface TravellMapper {
	//@Select("select * from tbl_board where bno > 0")
	
	//게시판 목록
	public List<TravellVO> getList();
	
	//게시판 페이징
	public List<TravellVO> getListWithPaging(GalleryPaging galleryPaging);
	
	//게시물 읽기
	public TravellVO read(Long t_bno);
	
	//게시물 작성
	public void insert(TravellVO travell);
	
	//게시물 수정
	public Integer update(TravellVO travell);
	
	//게시물 삭제
	public Integer delete(Long t_bno);
	
	//totalcount
	public int getTotalCount(GalleryPaging galleryPaging);
	
	public void updateReplyCnt(@Param("t_bno") Long t_bno,
			@Param("amount") int amount);
	
	//메인페이지에 최신글 불러오기
	public List<TravellVO> getLatestTravellList(GalleryPaging galleryPaging); 
}
