package com.powerfuljava.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TravellReplyPageDTO {
	private int replyCnt;
	private List<TravellReplyVO> list;
}
