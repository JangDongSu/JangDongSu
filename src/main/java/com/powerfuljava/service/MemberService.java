package com.powerfuljava.service;

import com.powerfuljava.domain.MemberVO;

public interface MemberService {
	public MemberVO get(String userId);
	
	public int register(MemberVO member);
	
	public int registerAuth(String userId);
	
	public int modify(MemberVO member);
	
	//public int idCheck(MemberVO member) throws Exception;
}
