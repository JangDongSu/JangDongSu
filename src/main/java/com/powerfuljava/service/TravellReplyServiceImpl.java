package com.powerfuljava.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.powerfuljava.domain.GalleryPaging;
import com.powerfuljava.domain.TravellReplyPageDTO;
import com.powerfuljava.domain.TravellReplyVO;
import com.powerfuljava.mapper.TravellMapper;
import com.powerfuljava.mapper.TravellReplyMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service
@AllArgsConstructor
@Log4j
public class TravellReplyServiceImpl implements TravellReplyService{
	
	private TravellReplyMapper travellReplyMapper;
	private TravellMapper travellMapper;
	
	@Transactional
	//��� �ۼ�
	@Override
	public int register(TravellReplyVO travellReply) {
		log.info("register...." + travellReply);
		travellMapper.updateReplyCnt(travellReply.getT_bno(),1);
		return travellReplyMapper.insert(travellReply);
	}
	
	//��� �б�
	@Override
	public TravellReplyVO get(Long t_rno) {
		log.info("get...." + t_rno);
		return travellReplyMapper.read(t_rno);
	}
	
	//��� ����
	@Override
	public int modify(TravellReplyVO travellReply) {
		log.info("modify...." + travellReply);
		return travellReplyMapper.update(travellReply);
	}
	
	@Transactional
	//��� ����
	@Override
	public int remove(Long t_rno) {
		log.info("remove...." + t_rno);
		TravellReplyVO travellReply = travellReplyMapper.read(t_rno);
		travellMapper.updateReplyCnt(travellReply.getT_bno(), -1);
		return travellReplyMapper.delete(t_rno);
	}
	
	//��� ��� - �Խ��� �б� ������
	@Override
	public List<TravellReplyVO> getList(GalleryPaging galleryPaging, Long t_bno){
		log.info("get Reply List of a Travell...." + t_bno);
		return travellReplyMapper.getListWithPaging(galleryPaging, t_bno);
	}
	
	//��� ��� - ����¡
	@Override
	public TravellReplyPageDTO getListPage(GalleryPaging galleryPaging, Long t_bno){
		log.info("get Reply List of a Travell...." + t_bno);
		galleryPaging.calOffset();
		return new TravellReplyPageDTO(
				travellReplyMapper.getCountByT_bno(t_bno),
				travellReplyMapper.getListWithPaging(galleryPaging, t_bno));
		}
}
