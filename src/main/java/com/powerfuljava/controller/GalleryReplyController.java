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

import com.powerfuljava.domain.GalleryReplyPageDTO;
import com.powerfuljava.domain.GalleryReplyVO;
import com.powerfuljava.domain.GalleryPaging;
import com.powerfuljava.service.GalleryReplyService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@RequestMapping("/galleryreplies/")
@RestController
@Log4j
@AllArgsConstructor
public class GalleryReplyController {
	private GalleryReplyService galleryReplyService;
	
	//댓글 작성
	@PreAuthorize("isAuthenticated()")
	@PostMapping(value = "/new", consumes = "application/json", produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> create(@RequestBody GalleryReplyVO galleryReply){
		log.info("galleryReplyVO : " + galleryReply);
		int insertCount = galleryReplyService.register(galleryReply);
		log.info("Reply INSERT COUNT : " + insertCount);
		return insertCount == 1 ? 
				new ResponseEntity<>("Reply success ", HttpStatus.OK) : 
					new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	//댓글 리스트 불러오기(게시판 읽기 페이지) - 댓글 목록 페이징
	@GetMapping(value = "/pages/{p_bno}/{page}", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<GalleryReplyPageDTO> getList(@PathVariable("p_bno") Long p_bno, @PathVariable("page") int page){
		log.info("getList................");
		GalleryPaging galleryPaging =  new GalleryPaging(page,10);
		log.info(galleryPaging);
		return new ResponseEntity<>(galleryReplyService.getListPage(galleryPaging, p_bno), HttpStatus.OK);
	}
	
	//특정 댓글 보기
	@GetMapping(value = "/{p_rno}", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<GalleryReplyVO> get(@PathVariable("p_rno") Long p_rno){
		log.info("get............." + p_rno);
		return new ResponseEntity<>(galleryReplyService.get(p_rno), HttpStatus.OK);
	}
	
	//댓글 지우기
	@PreAuthorize("principal.username == #galleryReply.replyer")
	@DeleteMapping(value = "/{p_rno}", produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> remove(@RequestBody GalleryReplyVO galleryReply, @PathVariable("p_rno") Long p_rno){
		log.info("remove : " + p_rno);
		log.info("answerer : " + galleryReply.getReplyer());
		return galleryReplyService.remove(p_rno) == 1
				? new ResponseEntity<>("remove success ", HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	//댓글 수정
	@PreAuthorize("principal.username == #galleryReply.replyer")
	@RequestMapping(method = {RequestMethod.PUT, RequestMethod.PATCH}, value = "/{p_rno}", 
			consumes = "application/json", produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> modify(@RequestBody GalleryReplyVO galleryReply, @PathVariable("p_rno") Long p_rno){
		//reply.setP_rno(p_rno);
		
		log.info("p_rno: " + p_rno);
		log.info("modify: " + galleryReply);
		
		return galleryReplyService.modify(galleryReply) == 1
				? new ResponseEntity<>("reply modify success", HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
