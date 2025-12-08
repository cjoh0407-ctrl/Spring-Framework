package org.zerock.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.zerock.dto.MemberDTO;
import org.zerock.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller	//Bean 등록 해줌 -> 등록된다는 것은 객체생성을 자동으로 스프링이 해준다?
@RequestMapping("/member")
@RequiredArgsConstructor	
@Log4j2
public class MemberController {

	private final MemberService service;
	
	// 요청 - localhost:8080/member/list
	// 응답 - WEB-INF/views/member/list.jsp
	@GetMapping("/list")
	public void list(Model model) {
		List<MemberDTO> memberList = service.getList();
		
		model.addAttribute("memberList", memberList);
	}
	
	
	
	// 요청 - localhost:8080/member/register
	// 응답 - WEB-INF/views/member/register.jsp
	@GetMapping("/register")
	public void registerGet() {
		log.info("register get");
	}
	
	
	
	// 요청 - localhost:8080/member/register
	// 응답 - localhost:8080/member/list
	@PostMapping("/register")
	public String registerPost(MemberDTO dto) {
		
		service.insert(dto);
		
		return "redirect:/member/list";
	}
	
	
	
	@GetMapping("/read/{mno}")
	public String read(@PathVariable("mno") int mno, Model model) {
		
		MemberDTO member = service.memberById(mno);
		model.addAttribute("member", member);
		
		return "member/read";
	}
	
	
	
	@PostMapping("/modify")
	public String modifyPost(MemberDTO dto) {
		
		service.update(dto);
		
		return "redirect:/member/list";
	}
	
	
	
	@PostMapping("/delete")
	public String deletePost(@RequestParam("mno") int mno) {
		
		service.delete(mno);
		
		return "redirect:/member/list";
	}
}


















