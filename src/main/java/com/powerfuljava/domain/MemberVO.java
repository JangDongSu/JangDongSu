package com.powerfuljava.domain;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class MemberVO {
	private String userId;
	private String userPw;
	private String userName;
	
	//주소 - Daum 주소 api 사용
	private String zipCode;
	private String address;
	private String addressDetail;
	
	
	//휴대폰번호
	private String phone;
	
	//이메일 주소
	private String email;
	
	private String gender;
	private boolean enabled;
	
	private Date regDate;
	private Date updateDate;
	private List<AuthVO> authList;
}
