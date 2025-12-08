package org.zerock.mapper;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.zerock.dto.TodoDTO;

import lombok.extern.log4j.Log4j2;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j2
public class TodoMapperTest {
	
	@Autowired
	TodoMapper todoMapper;

	@Test
	void insertTodo() {
		TodoDTO dto = TodoDTO.builder()
						.title("test3")
						.description("test3")
						.build();
		
		todoMapper.insertTodo(dto);
		
		log.info(dto);
	}
	
	@Test
	void selectTodoList() {
		List<TodoDTO> todoList = todoMapper.selectTodoList();
		
		for(TodoDTO dto : todoList) {
			log.info(dto);
		}
	}
	
	@Test
	void selectTodoOneById() {
		
		TodoDTO dto = todoMapper.selectOneById(1);
		
		log.info(dto);
	}
	
	
			
	@Test
	void updateTodo() {
		
		TodoDTO dto = TodoDTO.builder()
				.id(1)
				.title("test3")
				.description("test3")
				.build();
		
		todoMapper.updateTodo(dto);
		log.info(dto);
		
	}
	
	@Test
	void deleteTodo() {
		
		todoMapper.deleteTodo(2);
		
	}
	
}
