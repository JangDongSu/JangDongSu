package com.powerfuljava.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.powerfuljava.domain.GalleryPaging;
import com.powerfuljava.domain.TravellAttachVO;
import com.powerfuljava.domain.TravellVO;
import com.powerfuljava.mapper.TravellAttachMapper;
import com.powerfuljava.mapper.TravellMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service
@AllArgsConstructor
@Log4j
public class TravellServiceImpl implements TravellService{

	private TravellMapper mapper;
	private TravellAttachMapper attachMapper;
	
	@Override
	public void register(TravellVO travell) {
		log.info("register...." + travell);
		mapper.insert(travell);
		
		if(travell.getAttachList() == null || travell.getAttachList().size() <=0) {
			return;
		}
		travell.getAttachList().forEach(attach -> {
			attach.setT_bno(travell.getT_bno());
			attachMapper.insert(attach);
		});
	}

	@Override
	public TravellVO get(Long t_bno) {
		log.info("get" + t_bno);
		return mapper.read(t_bno);
	}
	
	@Transactional
	@Override
	public boolean modify(TravellVO travell) {
		log.info("modify" + travell);
		attachMapper.deleteAll(travell.getT_bno());
		boolean modifyResult = mapper.update(travell) == 1;
		if(modifyResult && travell.getAttachList() != null) {
			travell.getAttachList().forEach(attach->{
				attach.setT_bno(travell.getT_bno());
				attachMapper.insert(attach);
			});
		}
		return modifyResult;
	}

	@Override
	public boolean remove(Long t_bno) {
		log.info("delete........" + t_bno);
		attachMapper.deleteAll(t_bno); //업로드된 첨부파일을 찾아 삭제
		return mapper.delete(t_bno) == 1;
	}

	@Override
	public List<TravellVO> getList(GalleryPaging galleryPaging) {
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
	public List<TravellAttachVO> getAttachList(Long t_bno) {
		log.info("get attach list by t_bno : " + t_bno);
		return attachMapper.findByT_Bno(t_bno);
	}
	
	@Override
	public List<TravellVO> getLatestTravellList(GalleryPaging galleryPaging) {
		log.info("getLatestGallery");
		return mapper.getLatestTravellList(galleryPaging);
	}
}
