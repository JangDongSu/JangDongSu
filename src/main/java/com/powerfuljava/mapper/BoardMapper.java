package com.powerfuljava.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
//import org.apache.ibatis.annotations.Select;

import com.powerfuljava.domain.BoardVO;
import com.powerfuljava.domain.Criteria;

public interface BoardMapper {
	//@Select("select * from tbl_board where bno > 0")
	
	//�Խ��� ���
	public List<BoardVO> getList();
	
	//�Խ��� ����¡
	public List<BoardVO> getListWithPaging(Criteria criteria);
	
	//�Խù� �б�
	public BoardVO read(Long bno);
	
	//�Խù� �ۼ�
	public void insert(BoardVO board);
	
	//�Խù� ����
	public Integer update(BoardVO board);
	
	//�Խù� ����
	public Integer delete(Long bno);
	
	//�Խù� ��ȸ��
	public int getViewCount(Long bno);
	
	//totalcount
	public int getTotalCount(Criteria criteria);
	
	public void updateReplyCnt(@Param("bno") Long bno,
			@Param("amount") int amount);
}
