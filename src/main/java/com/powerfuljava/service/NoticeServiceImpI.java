package com.powerfuljava.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.powerfuljava.domain.Criteria;
import com.powerfuljava.domain.NoticeAttachVO;
//import com.powerfuljava.domain.LatestCriteria;
import com.powerfuljava.domain.NoticeVO;
import com.powerfuljava.mapper.NoticeAttachMapper;
import com.powerfuljava.mapper.NoticeMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service
@AllArgsConstructor
@Log4j
public class NoticeServiceImpI implements NoticeService{
	private NoticeMapper mapper;
	private NoticeAttachMapper attachMapper;
	
	@Override
	public void register(NoticeVO notice) {
		log.info("register" + notice);
		mapper.insert(notice);
		
		if(notice.getAttachList() == null || notice.getAttachList().size() <=0) {
			return;
		}
		notice.getAttachList().forEach(attach -> {
			attach.setN_bno(notice.getN_bno());
			attachMapper.insert(attach);
		});
	}
	
	@Override
	public List<NoticeVO> getList(Criteria criteria) {
		criteria.calOffset();
		log.info("getList");
		return mapper.getListWithPaging(criteria);
	}
	
	@Override
	public NoticeVO get(Long n_bno) {
		log.info("get" + n_bno);
		return mapper.read(n_bno);
	}
	
	@Transactional
	@Override
	public boolean modify(NoticeVO notice) {
		log.info("modify" + notice);
		attachMapper.deleteAll(notice.getN_bno());
		boolean modifyResult = mapper.update(notice) == 1;
		if(modifyResult && notice.getAttachList()!=null) {
			notice.getAttachList().forEach(attach->{
				attach.setN_bno(notice.getN_bno());
				attachMapper.insert(attach);
			});
		}
		return modifyResult ;
	}
	
	@Override
	public boolean remove(Long n_bno) {
		log.info("delete........" + n_bno);
		attachMapper.deleteAll(n_bno);
		return mapper.delete(n_bno) == 1;
	}
	
	@Override
	public int getTotalCount(Criteria criteria){
		log.info("getTotalCount......");
		return mapper.getTotalCount(criteria);
	}
	
	@Override
	public List<NoticeVO> getLatestList(Criteria criteria) {
		//latestCriteria.calOffset();
		log.info("getLatestList");
		return mapper.getLatestList(criteria);
	}
	
	@Override
	public List<NoticeAttachVO> getAttachList(Long n_bno) {
		log.info("get attach list by bno : " + n_bno);
		return attachMapper.findByN_Bno(n_bno);
	}
	
	@Override
	public int getViewCount(Long n_bno) {
		log.info("viewCount.....");
		return mapper.getViewCount(n_bno);
	}
}
