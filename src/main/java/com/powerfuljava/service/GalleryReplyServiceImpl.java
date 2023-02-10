package com.powerfuljava.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.powerfuljava.domain.GalleryReplyPageDTO;
import com.powerfuljava.domain.GalleryReplyVO;
import com.powerfuljava.domain.GalleryPaging;
import com.powerfuljava.mapper.GalleryMapper;
import com.powerfuljava.mapper.GalleryReplyMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service
@AllArgsConstructor
@Log4j
public class GalleryReplyServiceImpl implements GalleryReplyService{
	
	private GalleryReplyMapper galleryReplyMapper;
	private GalleryMapper galleryMapper;
	
	@Transactional
	//¥Ò±€ ¿€º∫
	@Override
	public int register(GalleryReplyVO galleryReply) {
		log.info("register...." + galleryReply);
		galleryMapper.updateReplyCnt(galleryReply.getP_bno(),1);
		return galleryReplyMapper.insert(galleryReply);
	}
	
	//¥Ò±€ ¿–±‚
	@Override
	public GalleryReplyVO get(Long p_rno) {
		log.info("get...." + p_rno);
		return galleryReplyMapper.read(p_rno);
	}
	
	//¥Ò±€ ºˆ¡§
	@Override
	public int modify(GalleryReplyVO galleryReply) {
		log.info("modify...." + galleryReply);
		return galleryReplyMapper.update(galleryReply);
	}
	
	@Transactional
	//¥Ò±€ ªË¡¶
	@Override
	public int remove(Long p_rno) {
		log.info("remove...." + p_rno);
		GalleryReplyVO galleryReply = galleryReplyMapper.read(p_rno);
		galleryMapper.updateReplyCnt(galleryReply.getP_bno(), -1);
		return galleryReplyMapper.delete(p_rno);
	}
	
	//¥Ò±€ ∏Ò∑œ - ∞‘Ω√∆« ¿–±‚ ∆‰¿Ã¡ˆ
	@Override
	public List<GalleryReplyVO> getList(GalleryPaging galleryPaging, Long p_bno){
		log.info("get Reply List of a Gallery...." + p_bno);
		return galleryReplyMapper.getListWithPaging(galleryPaging, p_bno);
	}
	
	//¥Ò±€ ∏Ò∑œ - ∆‰¿Ã¬°
	@Override
	public GalleryReplyPageDTO getListPage(GalleryPaging galleryPaging, Long p_bno){
		log.info("get Reply List of a gallery...." + p_bno);
		galleryPaging.calOffset();
		return new GalleryReplyPageDTO(
				galleryReplyMapper.getCountByP_bno(p_bno),
				galleryReplyMapper.getListWithPaging(galleryPaging, p_bno));
		}
}
