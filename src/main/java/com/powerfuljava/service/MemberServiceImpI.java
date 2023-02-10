package com.powerfuljava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.powerfuljava.domain.MemberVO;
import com.powerfuljava.mapper.MemberMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service
@AllArgsConstructor
@Log4j
public class MemberServiceImpI implements MemberService{

	@Autowired
	private PasswordEncoder pwEncoder;
	private MemberMapper mapper;
	
	//���̵�
	@Override
	public MemberVO get(String userId) {
		log.info("Member get........."+ userId);
		return mapper.read(userId);
	}
	
	//ȸ�������ϱ�
	@Override
	public int register(MemberVO member) {
		member.setUserPw(pwEncoder.encode(member.getUserPw()));
		log.info("Member register........."+ member);
		return mapper.insert(member);
	}
	
	//ȸ������
	@Override
	public int registerAuth(String userId) {
		log.info("register Auth : "+ userId);
		return mapper.insertAuth(userId);
	}
	
	//ȸ����������
	@Override
	public int modify(MemberVO member) {
		member.setUserPw(pwEncoder.encode(member.getUserPw()));
		log.info("Member register........."+ member);
		return mapper.update(member);
		}
	
	//���̵� �ߺ�üũ mapper����
	/*@Override
	public int idCheck(MemberVO member) throws Exception{
		int result = mapper.idCheck(member);
		log.info("cnt : " + result);
		return result;
	}*/
}
