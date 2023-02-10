package com.powerfuljava.service;

import java.util.List;

import com.powerfuljava.domain.GalleryPaging;
import com.powerfuljava.domain.TravellAttachVO;
import com.powerfuljava.domain.TravellVO;

public interface TravellService {
	
	public void register(TravellVO travell);
	
	public TravellVO get(Long t_bno);
	
	public boolean modify(TravellVO travell);	
	
	public boolean remove(Long t_bno);
	
	public List<TravellVO> getList(GalleryPaging galleryPaging);
	
	public int getTotalCount(GalleryPaging galleryPaging);
	
	public List<TravellAttachVO> getAttachList(Long t_bno);
	
	public List<TravellVO> getLatestTravellList(GalleryPaging galleryPaging);
}
