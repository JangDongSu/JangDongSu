package com.powerfuljava.controller;

import java.text.DateFormat;
//import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.powerfuljava.domain.Criteria;
import com.powerfuljava.domain.GalleryPaging;
import com.powerfuljava.domain.GalleryVO;
//import com.powerfuljava.domain.MemberVO;
import com.powerfuljava.domain.NoticeVO;
import com.powerfuljava.domain.TravellVO;
import com.powerfuljava.mapper.GalleryMapper;
//import com.powerfuljava.mapper.MemberMapper;
import com.powerfuljava.mapper.NoticeMapper;
import com.powerfuljava.mapper.TravellMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;


/**
 * Handles requests for the application home page.
 */
@Controller
@Log4j
@AllArgsConstructor
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@Autowired
	private NoticeMapper noticeMapper;
	private GalleryMapper galleryMapper;
	private TravellMapper travellMapper;
	//private MemberMapper memberMapper;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model, HttpServletRequest request, Criteria criteria, GalleryPaging galleryPaging) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		//main notice
		List<NoticeVO> notice = noticeMapper.getLatestList(criteria);
		log.info("notice : " + criteria);
		model.addAttribute("notice", notice);
		
		//galleryLatest
		List<GalleryVO> gallery = galleryMapper.getLatestGallery(galleryPaging);
		log.info("gallery" + galleryPaging);
		model.addAttribute("gallery", gallery);
		
		//getLatestTravellList
		List<TravellVO> travell = travellMapper.getLatestTravellList(galleryPaging);
		log.info("travell" + galleryPaging);
		model.addAttribute("travell", travell);
		
		//³ª¸ÓÁö
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
}
