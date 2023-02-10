package com.powerfuljava.mapper;

import java.util.List;

import com.powerfuljava.domain.TravellAttachVO;

public interface TravellAttachMapper {
	public void insert(TravellAttachVO vo);
	
	public List<TravellAttachVO> findByT_Bno(Long t_bno);
	
	public void delete(String uuid);
	
	public void deleteAll(Long t_bno);
	
	public List<TravellAttachVO> getOldFiles();
}
