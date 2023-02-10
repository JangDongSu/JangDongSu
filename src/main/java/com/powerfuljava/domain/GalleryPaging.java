package com.powerfuljava.domain;

import org.springframework.web.util.UriComponentsBuilder;

import lombok.Data;

@Data
public class GalleryPaging {
	private int pageNum;
	private int amount;
	private int offset;
	
	private String type;
	private String keyword;
	
	public GalleryPaging() {
		this(1, 16);		//������ ����Ʈ ��
	}
	
	public GalleryPaging(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;	//�������� Page ��
	}
	
	public void calOffset() {
		if(pageNum < 0) {
			pageNum = 1;
		}
		offset = (pageNum - 1)*10;
	}
	
	public String[] getTypeArr() {
		return type == null ? new String[] {} : type.split("");
	}
	
	public String getListLink() {
		UriComponentsBuilder builder = UriComponentsBuilder.fromPath("")
				.queryParam("pageNum", this.pageNum)
				.queryParam("amount", this.getAmount())
				.queryParam("type", this.getType())
				.queryParam("keyword", this.getKeyword());
		return builder.toUriString();
	}
}