package com.powerfuljava.mapper;

import java.util.List;

import com.powerfuljava.domain.GalleryAttachVO;

public interface GalleryAttachMapper {
	public void insert(GalleryAttachVO vo);
	
	public List<GalleryAttachVO> findByP_Bno(Long p_bno);
	
	public void delete(String uuid);
	
	public void deleteAll(Long p_bno);
	
	public List<GalleryAttachVO> getOldFiles();
}
