package com.powerfuljava.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.powerfuljava.domain.Criteria;
import com.powerfuljava.domain.ReplyVO;

public interface ReplyMapper {
	//´ñ±Û ÀÛ¼º
	public int insert(ReplyVO reply);
	
	//´ñ±Û ÀÐ±â
	public ReplyVO read(Long rno);
	
	//´ñ±Û Áö¿ì±â
	public int delete(Long rno);
	
	//´ñ±Û ¼öÁ¤
	public int update(ReplyVO reply) ;
	
	//´ñ±Û ¸ñ·Ï
	public List<ReplyVO> getListWithPaging(@Param("criteria") Criteria criteria, @Param("bno") Long bno);
	
	//´ñ±Û ÆäÀÌÂ¡
	public int getCountByBno(Long bno);
	
	//totalcount
	public int getTotalCount(Criteria criteria);
}
