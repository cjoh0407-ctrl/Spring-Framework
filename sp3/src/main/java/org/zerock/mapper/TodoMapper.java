package org.zerock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.zerock.dto.TodoDTO;

@Mapper
public interface TodoMapper {

	void insertTodo(TodoDTO dto);
	//create
	
	List<TodoDTO> selectTodoList();
	
	TodoDTO selectOneById(int id);
	//read
	
	void updateTodo(TodoDTO dto);
	//update
	
	void deleteTodo(int id);
	//delete
	
	
	
	
}
