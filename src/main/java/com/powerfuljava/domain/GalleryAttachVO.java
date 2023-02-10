package com.powerfuljava.domain;

import lombok.Data;

@Data
public class GalleryAttachVO {
	private String uuid;
	private String uploadPath;
	private String fileName;
	private boolean fileType;
	private String image;
	
	private Long p_bno;
}
