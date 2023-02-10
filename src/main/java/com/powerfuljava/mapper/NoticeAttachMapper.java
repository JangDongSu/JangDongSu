package com.powerfuljava.mapper;

import java.util.List;

import com.powerfuljava.domain.NoticeAttachVO;

public interface NoticeAttachMapper {
	public void insert(NoticeAttachVO vo);
	
	public List<NoticeAttachVO> findByN_Bno(Long n_bno);
	
	public void delete(String uuid);
	
	public void deleteAll(Long n_bno);
	
	public List<NoticeAttachVO> getOldFiles();
}
