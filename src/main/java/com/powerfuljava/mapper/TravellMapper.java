package com.powerfuljava.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
//import org.apache.ibatis.annotations.Select;

import com.powerfuljava.domain.GalleryPaging;
import com.powerfuljava.domain.TravellVO;

public interface TravellMapper {
	//@Select("select * from tbl_board where bno > 0")
	
	//�Խ��� ���
	public List<TravellVO> getList();
	
	//�Խ��� ����¡
	public List<TravellVO> getListWithPaging(GalleryPaging galleryPaging);
	
	//�Խù� �б�
	public TravellVO read(Long t_bno);
	
	//�Խù� �ۼ�
	public void insert(TravellVO travell);
	
	//�Խù� ����
	public Integer update(TravellVO travell);
	
	//�Խù� ����
	public Integer delete(Long t_bno);
	
	//totalcount
	public int getTotalCount(GalleryPaging galleryPaging);
	
	public void updateReplyCnt(@Param("t_bno") Long t_bno,
			@Param("amount") int amount);
	
	//������������ �ֽű� �ҷ�����
	public List<TravellVO> getLatestTravellList(GalleryPaging galleryPaging); 
}
