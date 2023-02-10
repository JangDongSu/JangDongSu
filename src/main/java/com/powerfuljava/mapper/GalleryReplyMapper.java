package com.powerfuljava.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.powerfuljava.domain.GalleryReplyVO;
import com.powerfuljava.domain.GalleryPaging;

public interface GalleryReplyMapper {
	//´ñ±Û ÀÛ¼º
	public int insert(GalleryReplyVO galleryReply);
	
	//´ñ±Û ÀÐ±â
	public GalleryReplyVO read(Long p_rno);
	
	//´ñ±Û Áö¿ì±â
	public int delete(Long p_rno);
	
	//´ñ±Û ¼öÁ¤
	public int update(GalleryReplyVO galleryReply);
	
	//´ñ±Û ¸ñ·Ï
	public List<GalleryReplyVO> getListWithPaging(@Param("galleryPaging") GalleryPaging galleryPaging, @Param("p_bno") Long p_bno);
	
	//´ñ±Û ÆäÀÌÂ¡
	public int getCountByP_bno(Long p_bno);
	
	//totalcount
	public int getTotalCount(GalleryPaging galleryPaging);
}
