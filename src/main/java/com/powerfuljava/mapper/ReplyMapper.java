package com.powerfuljava.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.powerfuljava.domain.Criteria;
import com.powerfuljava.domain.ReplyVO;

public interface ReplyMapper {
	//��� �ۼ�
	public int insert(ReplyVO reply);
	
	//��� �б�
	public ReplyVO read(Long rno);
	
	//��� �����
	public int delete(Long rno);
	
	//��� ����
	public int update(ReplyVO reply) ;
	
	//��� ���
	public List<ReplyVO> getListWithPaging(@Param("criteria") Criteria criteria, @Param("bno") Long bno);
	
	//��� ����¡
	public int getCountByBno(Long bno);
	
	//totalcount
	public int getTotalCount(Criteria criteria);
}
