package org.zerock.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.zerock.dto.TodoDTO;
import org.zerock.service.TodoService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@RequiredArgsConstructor
@RequestMapping("/todo")
@Log4j2
public class TodoController {

	private final TodoService todoService;
	
	@GetMapping("/list")
	public void list(Model model) {
		List<TodoDTO> dtoList = todoService.selectTodoList();
		
		model.addAttribute("todoList", dtoList);
	}
	
	
	@GetMapping("/register")
	public void register() {
	
	}
	
	@PostMapping("/register")
	public String register(@RequestParam("title") String title,
							@RequestParam("description") String description) {
		
		TodoDTO dto = TodoDTO.builder()
				.title(title)
				.description(description)
				.build();
		
		todoService.insertTodo(dto);
		
		return "redirect:/todo/list";
	}
	
	@GetMapping("/read/{id}")
	public String read(@PathVariable("id") int id, Model model) {
		
		TodoDTO todo = todoService.selectOneById(id);
		model.addAttribute("todo", todo);
		
		return "/todo/read";
	}
	
	
	
	@PostMapping("/modify")
	public String modify(TodoDTO dto) {
		
		log.info("수정요청 DTO: " + dto);
		todoService.updateTodo(dto);
		log.info("수정 후 DTO: " + dto);
		
		return "redirect:/todo/list";
	}
	
	
	
	@PostMapping("/delete")
	public String delete(@RequestParam("id") int id) {
		
		log.info("삭제할 아이디 : " + id);
		todoService.deleteTodo(id);
		
		return "redirect:/todo/list";
	}
	
	
	
}
