package com.powerfuljava.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.powerfuljava.domain.BoardAttachVO;
import com.powerfuljava.domain.BoardVO;
import com.powerfuljava.domain.Criteria;
import com.powerfuljava.domain.PageDTO;
import com.powerfuljava.service.BoardService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/board/*")
@AllArgsConstructor	//Autowired 대신....
public class BoardController {
	private BoardService service;
	
	//게시물 리스트 불러오기
	@GetMapping("/list")
	public void list(Criteria criteria, Model model) {
		log.info("list" + criteria);
		model.addAttribute("list", service.getList(criteria));
		model.addAttribute("pageMaker", new PageDTO(criteria, 123));
	}
	// 게시물 등록
	@GetMapping("/register")
	@PreAuthorize("isAuthenticated()")
	public void register() {
		log.info("register");
	}
	@PostMapping("/register")
	@PreAuthorize("isAuthenticated()")
	public String register(BoardVO board, RedirectAttributes rttr) {
		log.info("register : " + board.getBno());
		if(board.getAttachList() != null) {
			board.getAttachList().forEach(attach -> log.info(attach));
		}
		service.register(board);
		rttr.addFlashAttribute("result", board.getBno());
		return "redirect:/board/list";
	}
	//게시물 조회
	@GetMapping({"/get", "/modify"})
	public void get(@RequestParam("bno") Long bno,
			@ModelAttribute("criteria") Criteria criteria,
			Model model) {
		log.info("/get or modify");
		
		//조회수 증가
		service.getViewCount(bno);
		
		model.addAttribute("board", service.get(bno));
	}
	
	//게시물 수정
	@PreAuthorize("principal.username == #board.writer")
	@PostMapping("/modify")
	public String modify(BoardVO board, /*@RequestParam(value = "bno", required=false)*//*@ModelAttribute("criteria")*/ Criteria criteria, 
			RedirectAttributes rttr) {
		log.info("modify" + board);
		board.setBno(board.getBno());
		if(service.modify(board)) {
			rttr.addFlashAttribute("result", "success");
		}
		/*rttr.addAttribute("pageNum", criteria.getPageNum());
		rttr.addAttribute("amount", criteria.getAmount());
		rttr.addAttribute("type", criteria.getType());
		rttr.addAttribute("keyword", criteria.getKeyword());*/
		
		return "redirect:/board/list"+ criteria.getListLink();
	}
		//게시물 삭제
		@PreAuthorize("principal.username == #writer")
		@PostMapping("/remove")
		public String remove(@RequestParam("bno") Long bno, /*@ModelAttribute("criteria")*/ Criteria criteria, 
			RedirectAttributes rttr, @RequestParam("writer") String writer) {
			log.info("remove" + bno);
			List<BoardAttachVO> attachList = service.getAttachList(bno);	//게시물 삭제시 첨부파일 삭제(2022-12-30 추가)
			if(service.remove(bno)) {
				deleteFiles(attachList);	//게시물 삭제시 첨부파일 삭제(2022-12-30 추가)
				rttr.addFlashAttribute("result", "success");
			}
			/*rttr.addAttribute("pageNum", criteria.getPageNum());
			rttr.addAttribute("amount", criteria.getAmount());
			rttr.addAttribute("type", criteria.getType());
			rttr.addAttribute("keyword", criteria.getKeyword());*/
			
			return "redirect:/board/list" + criteria.getListLink();
		}
		//게시물 삭제시 첨부파일과 함께 삭제
		private void deleteFiles(List<BoardAttachVO> attachList) {
			if(attachList == null || attachList.size() == 0) {
				return;
			}
			log.info("Delete attach files...");
			log.info(attachList);
			attachList.forEach(attach->{
				try {
					Path file = Paths.get(
							"e:\\upload\\" + attach.getUploadPath() + "\\" + attach.getUuid() + "_" + attach.getFileName());
					Files.deleteIfExists(file);
					if(Files.probeContentType(file).startsWith("image")) {
						Path thumbNail = Paths.get("e:\\upload\\" + attach.getUploadPath() + "\\s_" + attach.getUuid() + "_" + attach.getFileName());
						Files.delete(thumbNail);
					}
				}catch(Exception e){
					log.error("delete file error" + e.getMessage());
				}
			});
		}
		
		//게시물 읽기 - 첨부파일 리스트 불러오기
		@GetMapping(value = "/getAttachList", produces = MediaType.APPLICATION_JSON_VALUE)
		@ResponseBody
		public ResponseEntity<List<BoardAttachVO>> getAttachList(Long bno) {
			log.info("getAttachList" + bno);
			return new ResponseEntity<>(service.getAttachList(bno), HttpStatus.OK);
		}
}
