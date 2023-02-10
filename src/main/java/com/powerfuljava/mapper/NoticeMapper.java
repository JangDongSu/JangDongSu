package com.powerfuljava.mapper;

import java.util.List;

import com.powerfuljava.domain.Criteria;
//import com.powerfuljava.domain.LatestCriteria;

//import org.apache.ibatis.annotations.Select;

import com.powerfuljava.domain.NoticeVO;

public interface NoticeMapper {
	//@Select("select * from notice_board where n_bno > 0")
	public List<NoticeVO> getList();
	
	//�Խ��� ����¡
	public List<NoticeVO> getListWithPaging(Criteria criteria);
	
	//�Խù� �б�
	public NoticeVO read(Long n_bno);
		
	//�Խù� �ۼ�
	public void insert(NoticeVO notice);
		
	//�Խù� ����
	public Integer update(NoticeVO notice);
		
	//�Խù� ����
	public Integer delete(Long n_bno);
	
	public int getTotalCount(Criteria criteria);
	
	//�ֽű�
	public List<NoticeVO> getLatestList(Criteria criteria);
	
	public int getViewCount(Long n_bno);
}
