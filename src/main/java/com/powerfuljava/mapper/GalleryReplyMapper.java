package com.powerfuljava.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.powerfuljava.domain.GalleryReplyVO;
import com.powerfuljava.domain.GalleryPaging;

public interface GalleryReplyMapper {
	//��� �ۼ�
	public int insert(GalleryReplyVO galleryReply);
	
	//��� �б�
	public GalleryReplyVO read(Long p_rno);
	
	//��� �����
	public int delete(Long p_rno);
	
	//��� ����
	public int update(GalleryReplyVO galleryReply);
	
	//��� ���
	public List<GalleryReplyVO> getListWithPaging(@Param("galleryPaging") GalleryPaging galleryPaging, @Param("p_bno") Long p_bno);
	
	//��� ����¡
	public int getCountByP_bno(Long p_bno);
	
	//totalcount
	public int getTotalCount(GalleryPaging galleryPaging);
}
