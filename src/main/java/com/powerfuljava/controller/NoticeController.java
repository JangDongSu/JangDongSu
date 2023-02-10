package com.powerfuljava.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

//import java.util.List;

//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.powerfuljava.domain.Criteria;
import com.powerfuljava.domain.NoticeAttachVO;
import com.powerfuljava.domain.NoticeVO;
import com.powerfuljava.domain.PageDTO;
import com.powerfuljava.service.NoticeService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/notice/*")
@AllArgsConstructor
public class NoticeController {
	private NoticeService noticeservice;
	
	//getlist
	@GetMapping("/list")
	public void list(Model model, Criteria criteria) {
		log.info("list" + criteria);
		model.addAttribute("list", noticeservice.getList(criteria));
		model.addAttribute("pageMaker", new PageDTO(criteria, 123));
	}
	
	//작성
	@GetMapping("/register")
	@PreAuthorize("hasRole('ADMIN')")
	public void register() {
		log.info("register");
	}
	@PostMapping("/register")
	@PreAuthorize("hasRole('ADMIN')")
	public String register(NoticeVO notice, RedirectAttributes rttr) {
		log.info("register"+ notice.getN_bno());
		if(notice.getAttachList() != null) {
			notice.getAttachList().forEach(attach -> log.info(attach));
		}
		noticeservice.register(notice);
		rttr.addFlashAttribute("result", notice.getN_bno());
		return "redirect:/notice/list";
	}
	
	//get
	@GetMapping({"/get", "/modify"})
		public void get(@RequestParam("n_bno") Long n_bno,
				@ModelAttribute("criteria") Criteria criteria,
				Model model) {
			log.info("/get or modify");
			
			//조회수 증가
			noticeservice.getViewCount(n_bno);
			model.addAttribute("notice", noticeservice.get(n_bno));
		}
	
	//게시물 수정
	@PreAuthorize("principal.username == #notice.n_writer")
	@PostMapping("/modify")
	public String modify(NoticeVO notice, Criteria criteria, 
				RedirectAttributes rttr) {
			log.info("modify" + notice);
			if(noticeservice.modify(notice)) {
				rttr.addFlashAttribute("result", "success");
			}
			rttr.addAttribute("pageNum", criteria.getPageNum());
			rttr.addAttribute("amount", criteria.getAmount());
			rttr.addAttribute("type", criteria.getType());
			rttr.addAttribute("keyword", criteria.getKeyword());
			
			return "redirect:/notice/list"+ criteria.getListLink();
		}
	//게시물 삭제
	@PreAuthorize("principal.username == #n_writer")
	@PostMapping("/remove")
	public String remove(@RequestParam("n_bno") Long n_bno, Criteria criteria, 
		RedirectAttributes rttr, @RequestParam("n_writer") String n_writer) {
		log.info("remove" + n_bno);
				
		List<NoticeAttachVO> attachList = noticeservice.getAttachList(n_bno);	//게시물 삭제시 첨부파일 삭제(2022-12-30 추가)
		if(noticeservice.remove(n_bno)) {
			deleteFiles(attachList);	//게시물 삭제시 첨부파일 삭제(2022-12-30 추가)
			rttr.addFlashAttribute("result", "success");
		}
			rttr.addAttribute("pageNum", criteria.getPageNum());
			rttr.addAttribute("amount", criteria.getAmount());
			rttr.addAttribute("type", criteria.getType());
			rttr.addAttribute("keyword", criteria.getKeyword());
				
			return "redirect:/notice/list" + criteria.getListLink();
		}
	
	//게시물 읽기 - 첨부파일 리스트 불러오기
	@GetMapping(value = "/getAttachList", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<NoticeAttachVO>> getAttachList(Long n_bno) {
		log.info("getAttachList" + n_bno);
		return new ResponseEntity<>(noticeservice.getAttachList(n_bno), HttpStatus.OK);
	}
	
	//게시물 삭제시 첨부파일과 함께 삭제
	private void deleteFiles(List<NoticeAttachVO> attachList) {
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
	
	
}
