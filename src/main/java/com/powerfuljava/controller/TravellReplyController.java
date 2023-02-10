package com.powerfuljava.controller;

//import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



import com.powerfuljava.domain.GalleryPaging;
import com.powerfuljava.domain.TravellReplyPageDTO;
import com.powerfuljava.domain.TravellReplyVO;
import com.powerfuljava.service.TravellReplyService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@RequestMapping("/travellreplies/")
@RestController
@Log4j
@AllArgsConstructor
public class TravellReplyController {
	private TravellReplyService travellReplyService;
	
	//댓글 작성
	@PreAuthorize("isAuthenticated()")
	@PostMapping(value = "/new", consumes = "application/json", produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> create(@RequestBody TravellReplyVO travellReply){
		log.info("travellReplyVO : " + travellReply);
		int insertCount = travellReplyService.register(travellReply);
		log.info("Reply INSERT COUNT : " + insertCount);
		return insertCount == 1 ? 
				new ResponseEntity<>("Reply success ", HttpStatus.OK) : 
					new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	//댓글 리스트 불러오기(게시판 읽기 페이지) - 댓글 목록 페이징
	@GetMapping(value = "/pages/{t_bno}/{page}", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<TravellReplyPageDTO> getList(@PathVariable("t_bno") Long t_bno, @PathVariable("page") int page){
		log.info("getList................");
		GalleryPaging galleryPaging =  new GalleryPaging(page,10);
		log.info(galleryPaging);
		return new ResponseEntity<>(travellReplyService.getListPage(galleryPaging, t_bno), HttpStatus.OK);
	}
	
	//특정 댓글 보기
	@GetMapping(value = "/{t_rno}", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<TravellReplyVO> get(@PathVariable("t_rno") Long t_rno){
		log.info("get............." + t_rno);
		return new ResponseEntity<>(travellReplyService.get(t_rno), HttpStatus.OK);
	}
	
	//댓글 지우기
	@PreAuthorize("principal.username == #travellReply.replyer")
	@DeleteMapping(value = "/{t_rno}", produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> remove(@RequestBody TravellReplyVO travellReply, @PathVariable("t_rno") Long t_rno){
		log.info("remove : " + t_rno);
		log.info("answerer : " + travellReply.getReplyer());
		return travellReplyService.remove(t_rno) == 1
				? new ResponseEntity<>("remove success ", HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	//댓글 수정
	@PreAuthorize("principal.username == #travellReply.replyer")
	@RequestMapping(method = {RequestMethod.PUT, RequestMethod.PATCH}, value = "/{t_rno}", 
			consumes = "application/json", produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> modify(@RequestBody TravellReplyVO travellReply, @PathVariable("t_rno") Long t_rno){
		//reply.setP_rno(p_rno);
		
		log.info("p_rno: " + t_rno);
		log.info("modify: " + travellReply);
		
		return travellReplyService.modify(travellReply) == 1
				? new ResponseEntity<>("reply modify success", HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
