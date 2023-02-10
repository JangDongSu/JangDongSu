package com.powerfuljava.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
//import org.apache.ibatis.annotations.Select;

import com.powerfuljava.domain.GalleryPaging;
import com.powerfuljava.domain.GalleryVO;

public interface GalleryMapper {
	//@Select("select * from tbl_board where bno > 0")
	
	//게시판 목록
	public List<GalleryVO> getList();
	
	//게시판 페이징
	public List<GalleryVO> getListWithPaging(GalleryPaging galleryPaging);
	
	//게시물 읽기
	public GalleryVO read(Long p_bno);
	
	//게시물 작성
	public void insert(GalleryVO gallery);
	
	//게시물 수정
	public Integer update(GalleryVO gallery);
	
	//게시물 삭제
	public Integer delete(Long p_bno);
	
	//totalcount
	public int getTotalCount(GalleryPaging galleryPaging);
	
	public void updateReplyCnt(@Param("p_bno") Long p_bno,
			@Param("amount") int amount);
	
	//메인페이지에 최신글 불러오기
	public List<GalleryVO> getLatestGallery(GalleryPaging galleryPaging); 
}
