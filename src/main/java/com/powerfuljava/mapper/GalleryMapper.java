package com.powerfuljava.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
//import org.apache.ibatis.annotations.Select;

import com.powerfuljava.domain.GalleryPaging;
import com.powerfuljava.domain.GalleryVO;

public interface GalleryMapper {
	//@Select("select * from tbl_board where bno > 0")
	
	//�Խ��� ���
	public List<GalleryVO> getList();
	
	//�Խ��� ����¡
	public List<GalleryVO> getListWithPaging(GalleryPaging galleryPaging);
	
	//�Խù� �б�
	public GalleryVO read(Long p_bno);
	
	//�Խù� �ۼ�
	public void insert(GalleryVO gallery);
	
	//�Խù� ����
	public Integer update(GalleryVO gallery);
	
	//�Խù� ����
	public Integer delete(Long p_bno);
	
	//totalcount
	public int getTotalCount(GalleryPaging galleryPaging);
	
	public void updateReplyCnt(@Param("p_bno") Long p_bno,
			@Param("amount") int amount);
	
	//������������ �ֽű� �ҷ�����
	public List<GalleryVO> getLatestGallery(GalleryPaging galleryPaging); 
}
