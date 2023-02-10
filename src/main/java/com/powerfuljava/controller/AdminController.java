package com.powerfuljava.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.powerfuljava.domain.Criteria;
//import com.powerfuljava.domain.AuthVO;
import com.powerfuljava.domain.MemberVO;
import com.powerfuljava.domain.NoticeVO;
import com.powerfuljava.mapper.MemberMapper;
import com.powerfuljava.mapper.NoticeMapper;
import com.powerfuljava.service.NoticeService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/admin/*")
@AllArgsConstructor
public class AdminController {
	@Autowired
	private MemberMapper memberMapper;
	private NoticeMapper noticeMapper; 
	
	private NoticeService noticeService;
		
	@GetMapping("/index")
	public String index(Model model) {
		//회원
		List<MemberVO> member = memberMapper.getUserId();
		log.info("member : " + member);
		model.addAttribute("member", member);
		
		//권한
		/*List<AuthVO> auth = memberMapper.getAuthList();
		log.info("auth : " + auth);
		model.addAttribute("auth", auth);*/
		return "admin/index";
	}
	
	//-------- member관리 ------------
	//list
	@GetMapping("/member/list")
	public String memberManage(Model model) {
		List<MemberVO> member = memberMapper.getUserId();
		log.info("member : " + member);
		model.addAttribute("member", member);
		return "admin/member/list";
	}
	//-------- 공지사항 ------------
	//list
	@GetMapping("/notice/list")
	public String notice(Model model) {
		List<NoticeVO> notice= noticeMapper.getList();
		log.info("notice : " + notice);
		model.addAttribute("notice", notice);
		return "admin/notice/list";
	}
	//register - 관리자전용
	@GetMapping("/notice/writer")
	@PreAuthorize("hasRole('ADMIN')")
	public void noticeRegister() {
		log.info("noticeRegister");
	}
	@PostMapping("/notice/register")
	@PreAuthorize("hasRole('ADMIN')")
	public String noticeRegister(NoticeVO notice, RedirectAttributes rttr) {
		log.info("register" + notice.getN_bno());
		if(notice.getAttachList()!=null) {
			notice.getAttachList().forEach(attach -> log.info(attach));
		}
		noticeService.register(notice);
		rttr.addFlashAttribute("result", notice.getN_bno());
		return "redirect:/admin/notice/list";
	}
	
	//get or modify
	@GetMapping({"/notice/get", "/notice/modify"})
	public void noticeGet(@RequestParam("n_bno") Long n_bno, 
			@ModelAttribute("criteria") Criteria criteria, Model model) {
		log.info("/get or modify");
		model.addAttribute("notice", noticeService.get(n_bno));
	}
}
