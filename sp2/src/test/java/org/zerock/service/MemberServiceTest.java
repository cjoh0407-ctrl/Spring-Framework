package org.zerock.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.zerock.dto.MemberDTO;

import lombok.extern.log4j.Log4j2;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j2
class MemberServiceTest {

	@Autowired
	private MemberService memberService;
	
	@Test
	void testGetList() {
		memberService.getList()
		.forEach(member -> log.info(member));
	}// end getList

	@Test
	void testMemberById() {
		memberService.memberById(1); //나중에 숫자를 입력값으로 바꾸기
	}// end memberById

	@Test
	void testUpdate() {
		
		MemberDTO dto = MemberDTO.builder()
				.name("김길동")
				.password("0000")
				.email("qwer@qwer.com")
				.mno(3) // 나중에 숫자를 입력값으로 바꾸기
				.build();
		
		memberService.update(dto);
	}// end update

	@Test
	void testInsert() {
		
		MemberDTO dto = MemberDTO.builder()
				.name("백길동")
				.password("1234")
				.email("bbb@bbb.com")
				.build();
		
		memberService.insert(dto);
	}// end insert

	@Test
	void testDelete() {
		memberService.delete(9);
		// 나중에 숫자를 입력값으로 바꾸기
	}// end delete

}
