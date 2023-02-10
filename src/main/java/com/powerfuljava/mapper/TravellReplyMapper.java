package com.powerfuljava.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.powerfuljava.domain.GalleryPaging;
import com.powerfuljava.domain.TravellReplyVO;

public interface TravellReplyMapper {
	//��� �ۼ�
	public int insert(TravellReplyVO travellReply);
	
	//��� �б�
	public TravellReplyVO read(Long t_rno);
	
	//��� �����
	public int delete(Long t_rno);
	
	//��� ����
	public int update(TravellReplyVO travellReply);
	
	//��� ���
	public List<TravellReplyVO> getListWithPaging(@Param("galleryPaging") GalleryPaging galleryPaging, @Param("t_bno") Long t_bno);
	
	//��� ����¡
	public int getCountByT_bno(Long t_bno);
	
	//totalcount
	public int getTotalCount(GalleryPaging galleryPaging);
}
