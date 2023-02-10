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
	
	//아이디
	@Override
	public MemberVO get(String userId) {
		log.info("Member get........."+ userId);
		return mapper.read(userId);
	}
	
	//회원가입하기
	@Override
	public int register(MemberVO member) {
		member.setUserPw(pwEncoder.encode(member.getUserPw()));
		log.info("Member register........."+ member);
		return mapper.insert(member);
	}
	
	//회원권한
	@Override
	public int registerAuth(String userId) {
		log.info("register Auth : "+ userId);
		return mapper.insertAuth(userId);
	}
	
	//회원정보수정
	@Override
	public int modify(MemberVO member) {
		member.setUserPw(pwEncoder.encode(member.getUserPw()));
		log.info("Member register........."+ member);
		return mapper.update(member);
		}
	
	//아이디 중복체크 mapper접근
	/*@Override
	public int idCheck(MemberVO member) throws Exception{
		int result = mapper.idCheck(member);
		log.info("cnt : " + result);
		return result;
	}*/
}
