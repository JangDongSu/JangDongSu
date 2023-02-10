package com.powerfuljava.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GalleryReplyPageDTO {
	private int replyCnt;
	private List<GalleryReplyVO> list;
}
