package com.powerfuljava.controller;

import org.apache.maven.artifact.repository.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.powerfuljava.domain.MemberVO;
import com.powerfuljava.mapper.MemberMapper;
import com.powerfuljava.service.MemberService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@AllArgsConstructor
public class CommonController {
	
	private MemberService service;
	private MemberMapper mapper;
	
	@GetMapping("/accessError")
	public void accessDenied(Authentication auth, Model model) {
		log.info("access Denied : " + auth);
		model.addAttribute("msg", "Access Denied");
	}
	
	@GetMapping("/customLogin")
	public void loginInput(String error, String logout, Model model) {
		log.info("error : " + error);
		log.info("logout : " + logout);
		if(error != null) {
			model.addAttribute("error", "로그인 오류!! 다시 로그인 해주세요.");
		}
		if(logout != null) {
			model.addAttribute("logout", "로그아웃 되었습니다. 이용해 주셔서 감사합니다. ");
		}
	}
	
	@GetMapping("/customLogout")
	public void logoutGET() {
		log.info("custom logout");
	}
	
	@PostMapping("/customLogout")
	public String logoutPost() {
		log.info("post custom logout");
		return "redirect:/customLogin";
	}
	
	//회원가입
	@GetMapping("/customRegister")
	public void registerGet() {
		log.info("register.....get..");
	}
	
	@PostMapping("/customRegister")
	public String registerPost(@ModelAttribute MemberVO member) throws Exception{
		log.info("register........post....");
		log.info("register Member : " + member);
		/*int result = service.idCheck(member);
		try {
			if(result == 1) {
				return "/customRegister";
			}else if(result == 0) {
				service.register(member);
				service.registerAuth(member.getUserId());
			}
		}catch(Exception e) {
			throw new RuntimeException();
		}*/
		
		service.register(member);
		service.registerAuth(member.getUserId());
		return "redirect:/customLogin";
	}
	
	//회원정보수정
	@GetMapping("/customModify")
	public void modifyGet() {
		log.info("register.....get..");
	}
		
	@PostMapping("/customModify")
	public String modifyPost(@ModelAttribute MemberVO member) {
		log.info("modify........post....");
		log.info("modify Member : " + member);
		mapper.update(member);
		return "redirect:/customModify";
		}
	
	//아이디 중복 검사
	/*@ResponseBody
	@RequestMapping(value="/idCheck", method = RequestMethod.POST)
	public int idCheck(@ModelAttribute MemberVO member) throws Exception{
		int result = service.idCheck(member);
		log.info("idCheck...");
		return result;
	}*/
}
