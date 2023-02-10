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

import com.powerfuljava.domain.GalleryAttachVO;
import com.powerfuljava.domain.GalleryPaging;
import com.powerfuljava.domain.GalleryVO;
import com.powerfuljava.domain.PageDTO;
import com.powerfuljava.service.GalleryService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/gallery/*")
@AllArgsConstructor	//Autowired ���....
public class GalleryController {
	private GalleryService service;
	
	//�Խù� ����Ʈ �ҷ�����
	@GetMapping("/list")
	public void list(GalleryPaging galleryPaging, Model model) {
		log.info("list" + galleryPaging);
		
		model.addAttribute("list", service.getList(galleryPaging));
		model.addAttribute("pageMaker", new PageDTO(galleryPaging, 123));
	}
	// �Խù� ���
	@GetMapping("/register")
	@PreAuthorize("isAuthenticated()")
	public void register() {
		log.info("register");
	}
	@PostMapping("/register")
	@PreAuthorize("isAuthenticated()")
	public String register(GalleryVO gallery, RedirectAttributes rttr) {
		log.info("register : " + gallery.getP_bno());
		if(gallery.getAttachList() != null) {
			gallery.getAttachList().forEach(attach -> log.info(attach));
		}
		service.register(gallery);
		rttr.addFlashAttribute("result", gallery.getP_bno());
		return "redirect:/gallery/list";
	}
	//�Խù� ��ȸ
	@GetMapping({"/get", "/modify"})
	public void get(@RequestParam("p_bno") Long p_bno,
			@ModelAttribute("galleryPaging") GalleryPaging galleryPaging,
			Model model) {
		log.info("/get or modify");
		model.addAttribute("gallery", service.get(p_bno));
	}
	
	//�Խù� ����
	@PreAuthorize("principal.username == #gallery.writer")
	@PostMapping("/modify")
	public String modify(GalleryVO gallery, GalleryPaging galleryPaging, 
			RedirectAttributes rttr) {
		log.info("modify" + gallery);
		gallery.setP_bno(gallery.getP_bno());
		if(service.modify(gallery)) {
			rttr.addFlashAttribute("result", "success");
		}
		
		return "redirect:/gallery/list"+ galleryPaging.getListLink();
	}
		//�Խù� ����
		@PreAuthorize("principal.username == #writer")
		@PostMapping("/remove")
		public String remove(@RequestParam("p_bno") Long p_bno, GalleryPaging galleryPaging, 
			RedirectAttributes rttr, @RequestParam("writer") String writer) {
			log.info("remove" + p_bno);
			List<GalleryAttachVO> attachList = service.getAttachList(p_bno);	//�Խù� ������ ÷������ ����(2022-12-30 �߰�)
			if(service.remove(p_bno)) {
				deleteFiles(attachList);	//�Խù� ������ ÷������ ����(2022-12-30 �߰�)
				rttr.addFlashAttribute("result", "success");
			}
			
			return "redirect:/gallery/list" + galleryPaging.getListLink();
		}
		//�Խù� ������ ÷�����ϰ� �Բ� ����
		private void deleteFiles(List<GalleryAttachVO> attachList) {
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
		
		//�Խù� �б� - ÷������ ����Ʈ �ҷ�����
		@GetMapping(value = "/getAttachList", produces = MediaType.APPLICATION_JSON_VALUE)
		@ResponseBody
		public ResponseEntity<List<GalleryAttachVO>> getAttachList(Long p_bno) {
			log.info("getAttachList" + p_bno);
			return new ResponseEntity<>(service.getAttachList(p_bno), HttpStatus.OK);
		}
		
		//�Խù� ����Ʈ - ÷������ ����Ʈ �ҷ�����
		@GetMapping(value = "/getAttachListOnList", produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<Map<Long, List<GalleryAttachVO>>> getAttachListOnList(@RequestParam(value="list[]") List<Long> list){
				log.info("getAttachListOnList" + list.stream().collect(Collectors.toList()));
				Map<Long, List<GalleryAttachVO>> map = new HashMap<Long, List<GalleryAttachVO>>();
				list.stream().forEach(p_bno->map.put(p_bno, service.getAttachList(p_bno)));
				return new ResponseEntity<>(map, HttpStatus.OK);
				}
		
}
