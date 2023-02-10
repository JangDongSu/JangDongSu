package com.powerfuljava.security;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/*import com.powerfuljava.domain.MemberVO;
import com.powerfuljava.mapper.MemberMapper;*/

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml", 
		"file:src/main/webapp/WEB-INF/spring/security-context.xml"
	})
@Log4j
public class MemberTests {
	@Autowired
	private PasswordEncoder pwencoder;
	@Autowired
	private DataSource ds;
	
	@Test
	public void testInsertMember() {
		String sql = "insert into tbl_member(userid, userpw, username) values (?, ?, ?)";
		for(int i=0; i<10; i++) {
			Connection con = null;
			PreparedStatement pstmt = null;
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(sql);
				pstmt.setString(2, pwencoder.encode("pw" + i));
				if(i<8) {
					pstmt.setString(1, "user"+i);
					pstmt.setString(3, "일반사용자"+i);
				}else if(i<9) {
					pstmt.setString(1, "manager"+i);
					pstmt.setString(3, "운영자"+i);
				}else {
					pstmt.setString(1, "admin"+i);
					pstmt.setString(3, "관리자"+i);
				}
				pstmt.executeUpdate();
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				if(pstmt != null) {try {pstmt.close();} catch(Exception e) {}}
				if(con != null) {try {con.close();} catch(Exception e) {}}
			}
		}
	}
	
	@Test
	public void testIntertAuth() {
		String sql = "insert into tbl_member_auth(userid, auth) values (?, ?)";
		for(int i=0; i<10; i++) {
			Connection con = null;
			PreparedStatement pstmt = null;
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(sql);
				if(i<8) {
					pstmt.setString(1, "user"+i);
					pstmt.setString(2, "ROLE_USER"+i);
				}else if(i<9) {
					pstmt.setString(1, "manager"+i);
					pstmt.setString(2, "ROLE_MEMBER"+i);
				}else {
					pstmt.setString(1, "admin"+i);
					pstmt.setString(2, "ROLE_ADMIN"+i);
				}
				pstmt.executeUpdate();
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				if(pstmt != null) {try {pstmt.close();} catch(Exception e) {}}
				if(con != null) {try {con.close();} catch(Exception e) {}}
			}
		}
	}
	
	/*// -- 기존 레코드 삭제(게시물 삭제)
		@Test
		public void testDelete() {
			int count = mapper.delete(1L);
			log.info("delete count : " + count);
		}*/
		
	/*// -- 기존 레코드 read
	@Test
	public void testRead() {
		BoardVO board = mapper.read(1L);
		log.info(board);
	}
	@Test
	public void testPaging() {
		Criteria criteria = new Criteria();
		criteria.setPageNum(1);
		criteria.setAmount(10);
		criteria.calOffset();
		List<BoardVO> list = mapper.getListWithPaging(criteria);
		for(int i=0; i<list.size(); i++) {
			log.info(list.get(i));
		}	
	}*/
}
