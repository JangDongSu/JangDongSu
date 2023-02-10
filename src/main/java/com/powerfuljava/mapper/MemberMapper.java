package com.powerfuljava.mapper;

import java.util.List;

import com.powerfuljava.domain.AuthVO;
import com.powerfuljava.domain.MemberVO;

public interface MemberMapper {
	public MemberVO read(String userId);
	
	public int insert(MemberVO member);
	
	public int insertAuth(String userId);
	
	public int update(MemberVO member);
	
	//adminpage
	public List<MemberVO> getUserId();
	
	public List<AuthVO> getAuthList();
	
	//public int idCheck(MemberVO member);
	
}
