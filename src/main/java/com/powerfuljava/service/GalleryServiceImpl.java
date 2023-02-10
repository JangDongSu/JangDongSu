package com.powerfuljava.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.powerfuljava.domain.GalleryAttachVO;
import com.powerfuljava.domain.GalleryPaging;
import com.powerfuljava.domain.GalleryVO;
import com.powerfuljava.mapper.GalleryAttachMapper;
import com.powerfuljava.mapper.GalleryMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service
@AllArgsConstructor
@Log4j
public class GalleryServiceImpl implements GalleryService{

	private GalleryMapper mapper;
	private GalleryAttachMapper attachMapper;
	
	@Override
	public void register(GalleryVO gallery) {
		log.info("register...." + gallery);
		mapper.insert(gallery);
		
		if(gallery.getAttachList() == null || gallery.getAttachList().size() <=0) {
			return;
		}
		gallery.getAttachList().forEach(attach -> {
			attach.setP_bno(gallery.getP_bno());
			attachMapper.insert(attach);
		});
	}

	@Override
	public GalleryVO get(Long p_bno) {
		log.info("get" + p_bno);
		return mapper.read(p_bno);
	}
	
	@Transactional
	@Override
	public boolean modify(GalleryVO gallery) {
		log.info("modify" + gallery);
		attachMapper.deleteAll(gallery.getP_bno());
		boolean modifyResult = mapper.update(gallery) == 1;
		if(modifyResult && gallery.getAttachList() != null) {
			gallery.getAttachList().forEach(attach->{
				attach.setP_bno(gallery.getP_bno());
				attachMapper.insert(attach);
			});
		}
		return modifyResult;
		//return mapper.update(board) == 1;
	}

	@Override
	public boolean remove(Long p_bno) {
		log.info("delete........" + p_bno);
		attachMapper.deleteAll(p_bno); //업로드된 첨부파일을 찾아 삭제
		return mapper.delete(p_bno) == 1;
	}

	@Override
	public List<GalleryVO> getList(GalleryPaging galleryPaging) {
		galleryPaging.calOffset();
		log.info("getList......");
		return mapper.getListWithPaging(galleryPaging);
	}
	
	@Override
	public int getTotalCount(GalleryPaging galleryPaging){
		log.info("getTotalCount......");
		return mapper.getTotalCount(galleryPaging);
	}
	
	@Override
	public List<GalleryAttachVO> getAttachList(Long p_bno) {
		log.info("get attach list by bno : " + p_bno);
		return attachMapper.findByP_Bno(p_bno);
	}
	
	@Override
	public List<GalleryVO> getLatestGallery(GalleryPaging galleryPaging) {
		//
		log.info("getLatestGallery");
		return mapper.getLatestGallery(galleryPaging);
	}
}
