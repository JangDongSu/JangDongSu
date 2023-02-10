package com.powerfuljava.controller;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.WebApplicationContext;

import com.powerfuljava.domain.BoardVO;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml"
					 ,"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
@Log4j
@WebAppConfiguration
public class BoardControllerTests {

	@Autowired
	private WebApplicationContext context;
	
	private MockMvc mockMvc;
	
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}
	
	//게시물 목록
	/*@Test
	public void testList() throws Exception {
		log.info (mockMvc.perform(MockMvcRequestBuilders.get("/board/list"))
															.andReturn().getModelAndView().getModelMap());
	}*/
	
	// 게시물 목록 - 페이징
	@SuppressWarnings("unchecked")
	@Test
	public void testListPaging() throws Exception {
		ModelMap mm = mockMvc.perform(MockMvcRequestBuilders.get("/board/list")
		.param("pageNum", "3")
		.param("amount", "10"))
		.andReturn()
		.getModelAndView()
		.getModelMap();
		Object obj = mm.get("list");
		if(obj instanceof List) {
			List<BoardVO> list = (List<BoardVO>)obj;
			list.forEach(board->log.info(board));
		}
	}
	
	/*// 게시물 작성
	@Test
	public void testRegister() throws Exception {
		String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/board/register")
		.param("title" , "새글은 테스트 씨발")
		.param("content" , "새 콘텐츠는 새글에다 쳐여라 새꺄")
		.param("writer" , "new writer")).andReturn().getModelAndView().getViewName();
		log.info(resultPage);
	}*/
	
	/*// 게시물 가져오기
	@Test
	public void testGet() throws Exception {
		log.info("get 테스트를 진행한다 새끼야" + 
				mockMvc.perform(MockMvcRequestBuilders.get("/board/get")
		.param("bno" , "5"))
		.andReturn().
		getModelAndView().
		getModelMap());
	}*/
	
	/*// 게시물 수정
	@Test
	public void testModify() throws Exception {
		String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/board/modify")
		.param("bno" , "23")
		.param("title" , "고자라니!!!")
		.param("content" , "내가 고자라니!!!! ")
		.param("writer" , "심영")).andReturn().getModelAndView().getViewName();
		log.info(resultPage);
	}*/
	
	// 게시물 삭제
	/*@Test
	public void testRemove() throws Exception {
		//삭제하기 전 db에 게시물 번호 확인 할것
		String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/board/remove")
		.param("bno" , "21"))
		.andReturn()
		.getModelAndView()
		.getViewName();
		log.info(resultPage);
	}*/
	
}

