package com.powerfuljava.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.powerfuljava.domain.GalleryPaging;
import com.powerfuljava.domain.TravellReplyVO;

public interface TravellReplyMapper {
	//´ñ±Û ÀÛ¼º
	public int insert(TravellReplyVO travellReply);
	
	//´ñ±Û ÀÐ±â
	public TravellReplyVO read(Long t_rno);
	
	//´ñ±Û Áö¿ì±â
	public int delete(Long t_rno);
	
	//´ñ±Û ¼öÁ¤
	public int update(TravellReplyVO travellReply);
	
	//´ñ±Û ¸ñ·Ï
	public List<TravellReplyVO> getListWithPaging(@Param("galleryPaging") GalleryPaging galleryPaging, @Param("t_bno") Long t_bno);
	
	//´ñ±Û ÆäÀÌÂ¡
	public int getCountByT_bno(Long t_bno);
	
	//totalcount
	public int getTotalCount(GalleryPaging galleryPaging);
}
