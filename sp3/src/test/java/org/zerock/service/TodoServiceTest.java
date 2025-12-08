package org.zerock.service;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.zerock.dto.TodoDTO;
import org.zerock.mapper.TodoMapper;

import lombok.extern.log4j.Log4j2;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j2
public class TodoServiceTest {

	@Autowired
	private TodoMapper todoMapper;
	
	@Test
	void insertTodo() {
		TodoDTO dto = TodoDTO.builder()
				.title("serviceTest1")
				.description("serviceTest1")
				.build();
		
		todoMapper.insertTodo(dto);
		
		log.info(dto);
	}
	// end insert
	
	@Test
	void selectTodoList() {
		
		List<TodoDTO> dtoList = todoMapper.selectTodoList();
		
		for(TodoDTO dto : dtoList) {
			log.info(dto);
		}
	}
	// end selectList
	
	@Test
	void selectTodoOneById() {
		TodoDTO dto = todoMapper.selectOneById(5);
		
		log.info(dto);
	}
	// end selectOne
	
	@Test
	void updateTodo() {
		TodoDTO dto = TodoDTO.builder()
						.id(5)
						.title("serviceTest2")
						.description("serviceTest2")
						.build();
		
		todoMapper.updateTodo(dto);
		
		log.info(todoMapper.selectOneById(5));
	}
	// end update
	
	@Test
	void deleteTodo() {
		todoMapper.deleteTodo(5);
	}
}
