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

import com.powerfuljava.domain.Criteria;
import com.powerfuljava.domain.ReplyPageDTO;
import com.powerfuljava.domain.ReplyVO;
import com.powerfuljava.service.ReplyService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@RequestMapping("/replies/")
@RestController
@Log4j
@AllArgsConstructor
public class ReplyController {
	private ReplyService service;
	
	//댓글 작성
	@PreAuthorize("isAuthenticated()")
	@PostMapping(value = "/new", consumes = "application/json", produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> create(@RequestBody ReplyVO reply){
		log.info("ReplyVO : " + reply);
		int insertCount = service.register(reply);
		log.info("Reply INSERT COUNT : " + insertCount);
		return insertCount == 1 ? 
				new ResponseEntity<>("Reply success ", HttpStatus.OK) : 
					new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	//댓글 리스트 불러오기(게시판 읽기 페이지) - 댓글 목록 페이징
	@GetMapping(value = "/pages/{bno}/{page}", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<ReplyPageDTO> getList(@PathVariable("bno") Long bno, @PathVariable("page") int page){
		log.info("getList................");
		Criteria criteria =  new Criteria(page,10);
		log.info(criteria);
		return new ResponseEntity<>(service.getListPage(criteria, bno), HttpStatus.OK);
	}
	
	//특정 댓글 보기
	@GetMapping(value = "/{rno}", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<ReplyVO> get(@PathVariable("rno") Long rno){
		log.info("get............." + rno);
		return new ResponseEntity<>(service.get(rno), HttpStatus.OK);
	}
	
	//댓글 지우기
	@PreAuthorize("principal.username == #reply.replyer")
	@DeleteMapping(value = "/{rno}", produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> remove(@RequestBody ReplyVO reply, @PathVariable("rno") Long rno){
		log.info("remove : " + rno);
		log.info("replyer : " + reply.getReplyer());
		return service.remove(rno) == 1
				? new ResponseEntity<>("remove success ", HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	//댓글 수정
	@PreAuthorize("principal.username == #reply.replyer")
	@RequestMapping(method = {RequestMethod.PUT, RequestMethod.PATCH}, value = "/{rno}", 
			consumes = "application/json", produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> modify(@RequestBody ReplyVO reply, @PathVariable("rno") Long rno){
		//reply.setRno(rno);
		
		log.info("rno: " + rno);
		log.info("modify: " + reply);
		
		return service.modify(reply) == 1
				? new ResponseEntity<>("reply modify success", HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
