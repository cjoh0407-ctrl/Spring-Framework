package org.zerock.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.zerock.dto.TodoDTO;
import org.zerock.mapper.TodoMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class TodoService {

	
	private final TodoMapper todoMapper;
	
	public void insertTodo(TodoDTO dto) {
		todoMapper.insertTodo(dto);
	} 
	//end insert
	
	
	public List<TodoDTO> selectTodoList(){
		return todoMapper.selectTodoList();
	}
	// end selectList
	
	public TodoDTO selectOneById(int id) {
		return todoMapper.selectOneById(id);
	}
	// end selectOne
	
	public void updateTodo(TodoDTO dto) {
		log.info("Service에서 Mapper로 전달할 ID: " + dto.getId());
		todoMapper.updateTodo(dto);
	}
	// end update
	
	public void deleteTodo(int id) {
		todoMapper.deleteTodo(id);
	}
	// end delete
}
