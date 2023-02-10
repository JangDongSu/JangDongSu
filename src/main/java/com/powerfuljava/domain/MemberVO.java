package com.powerfuljava.domain;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class MemberVO {
	private String userId;
	private String userPw;
	private String userName;
	
	//�ּ� - Daum �ּ� api ���
	private String zipCode;
	private String address;
	private String addressDetail;
	
	
	//�޴�����ȣ
	private String phone;
	
	//�̸��� �ּ�
	private String email;
	
	private String gender;
	private boolean enabled;
	
	private Date regDate;
	private Date updateDate;
	private List<AuthVO> authList;
}
