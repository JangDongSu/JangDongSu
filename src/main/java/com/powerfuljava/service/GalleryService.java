package com.powerfuljava.service;

import java.util.List;

import com.powerfuljava.domain.GalleryAttachVO;
import com.powerfuljava.domain.GalleryPaging;
import com.powerfuljava.domain.GalleryVO;

public interface GalleryService {
	
	public void register(GalleryVO gallery);
	
	public GalleryVO get(Long p_bno);
	
	public boolean modify(GalleryVO gallery);	
	
	public boolean remove(Long p_bno);
	
	public List<GalleryVO> getList(GalleryPaging galleryPaging);
	
	public int getTotalCount(GalleryPaging galleryPaging);
	
	public List<GalleryAttachVO> getAttachList(Long p_bno);
	
	public List<GalleryVO> getLatestGallery(GalleryPaging galleryPaging);
}
