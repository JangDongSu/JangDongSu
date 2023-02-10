package com.powerfuljava.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.powerfuljava.domain.Criteria;
import com.powerfuljava.domain.ReplyPageDTO;
import com.powerfuljava.domain.ReplyVO;
import com.powerfuljava.mapper.BoardMapper;
import com.powerfuljava.mapper.ReplyMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service
@AllArgsConstructor
@Log4j
public class ReplyServicempi implements ReplyService{
	
	private ReplyMapper mapper;
	private BoardMapper boardMapper;
	
	@Transactional
	//��� �ۼ�
	@Override
	public int register(ReplyVO vo) {
		log.info("register...." + vo);
		boardMapper.updateReplyCnt(vo.getBno(),1);
		return mapper.insert(vo);
	}
	
	//��� �б�
	@Override
	public ReplyVO get(Long rno) {
		log.info("get...." + rno);
		return mapper.read(rno);
	}
	
	//��� ����
	@Override
	public int modify(ReplyVO vo) {
		log.info("modify...." + vo);
		return mapper.update(vo);
	}
	
	@Transactional
	//��� ����
	@Override
	public int remove(Long rno) {
		log.info("remove...." + rno);
		ReplyVO vo = mapper.read(rno);
		boardMapper.updateReplyCnt(vo.getBno(), -1);
		return mapper.delete(rno);
	}
	
	//��� ��� - �Խ��� �б� ������
	@Override
	public List<ReplyVO> getList(Criteria cri, Long bno){
		log.info("get Reply List of a Board...." + bno);
		return mapper.getListWithPaging(cri, bno);
	}
	
	//��� ��� - ����¡
	@Override
	public ReplyPageDTO getListPage(Criteria criteria, Long bno){
		log.info("get Reply List of a Board...." + bno);
		criteria.calOffset();
		return new ReplyPageDTO(
				mapper.getCountByBno(bno),
				mapper.getListWithPaging(criteria, bno));
		}
}
