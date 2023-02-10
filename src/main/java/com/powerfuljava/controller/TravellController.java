package com.powerfuljava.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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


import com.powerfuljava.domain.GalleryPaging;
import com.powerfuljava.domain.PageDTO;
import com.powerfuljava.domain.TravellAttachVO;
import com.powerfuljava.domain.TravellVO;
import com.powerfuljava.service.TravellService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/travell/*")
@AllArgsConstructor	
public class TravellController {
	private TravellService service;
	
	//게시물 리스트 불러오기
	@GetMapping("/list")
	public void list(GalleryPaging galleryPaging, Model model) {
		log.info("list" + galleryPaging);
		
		model.addAttribute("list", service.getList(galleryPaging));
		model.addAttribute("pageMaker", new PageDTO(galleryPaging, 123));
	}
	// 게시물 등록
	@GetMapping("/register")
	@PreAuthorize("isAuthenticated()")
	public void register() {
		log.info("register");
	}
	@PostMapping("/register")
	@PreAuthorize("isAuthenticated()")
	public String register(TravellVO travell, RedirectAttributes rttr) {
		log.info("register : " + travell.getT_bno());
		if(travell.getAttachList() != null) {
			travell.getAttachList().forEach(attach -> log.info(attach));
		}
		service.register(travell);
		rttr.addFlashAttribute("result", travell.getT_bno());
		return "redirect:/travell/list";
	}
	//게시물 조회
	@GetMapping({"/get", "/modify"})
	public void get(@RequestParam("t_bno") Long t_bno,
			@ModelAttribute("galleryPaging") GalleryPaging galleryPaging,
			Model model) {
		log.info("/get or modify");
		model.addAttribute("travell", service.get(t_bno));
	}
	
	//게시물 수정
	@PreAuthorize("principal.username == #travell.writer")
	@PostMapping("/modify")
	public String modify(TravellVO travell, GalleryPaging galleryPaging, 
			RedirectAttributes rttr) {
		log.info("modify" + travell);
		travell.setT_bno(travell.getT_bno());
		if(service.modify(travell)) {
			rttr.addFlashAttribute("result", "success");
		}
		
		return "redirect:/travell/list"+ galleryPaging.getListLink();
	}
		//게시물 삭제
		@PreAuthorize("principal.username == #writer")
		@PostMapping("/remove")
		public String remove(@RequestParam("t_bno") Long t_bno, GalleryPaging galleryPaging, 
			RedirectAttributes rttr, @RequestParam("writer") String writer) {
			log.info("remove" + t_bno);
			List<TravellAttachVO> attachList = service.getAttachList(t_bno);	//게시물 삭제시 첨부파일 삭제(2022-12-30 추가)
			if(service.remove(t_bno)) {
				
				deleteFiles(attachList);	//게시물 삭제시 첨부파일 삭제(2022-12-30 추가)
				rttr.addFlashAttribute("result", "success");
			}
			
			return "redirect:/travell/list" + galleryPaging.getListLink();
		}
		//게시물 삭제시 첨부파일과 함께 삭제
		private void deleteFiles(List<TravellAttachVO> attachList) {
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
		public ResponseEntity<List<TravellAttachVO>> getAttachList(Long t_bno) {
			log.info("getAttachList" + t_bno);
			return new ResponseEntity<>(service.getAttachList(t_bno), HttpStatus.OK);
		}
		
		//게시물 리스트 - 첨부파일 리스트 불러오기
		@GetMapping(value = "/getAttachListOnList", produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<Map<Long, List<TravellAttachVO>>> getAttachListOnList(@RequestParam(value="list[]") List<Long> list){
				log.info("getAttachListOnList" + list.stream().collect(Collectors.toList()));
				Map<Long, List<TravellAttachVO>> map = new HashMap<Long, List<TravellAttachVO>>();
				list.stream().forEach(t_bno->map.put(t_bno, service.getAttachList(t_bno)));
				return new ResponseEntity<>(map, HttpStatus.OK);
				}
		
}
