package org.zerock.mapper;

import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.zerock.dto.BoardDTO;

import lombok.extern.log4j.Log4j2;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j2
public class BoardMapperTests {

	@Autowired // 테스트 환경에서는 생성자 주입은 먹히지 않으니 Autowired로 하면된다.
	private BoardMapper boardMapper;
	
	@Test
	public void testInsert() {
		
		//setter 이용한 방식
//		BoardDTO dto = new BoardDTO(); 
//		dto.setTitle("title");
//		dto.setContent("Content");
//		dto.setWriter("user00");
		
		//builder 이용한 방식
		BoardDTO dto = BoardDTO.builder()
				.title("title2")
				.content("content2")
				.writer("user02")
				.build();
		
		int insertCount = boardMapper.insert(dto); // 1값이 들어오면 성공, 0이면 실패
		log.info("------------------------");
		log.info("insertCount : " + insertCount);
		
		log.info("BNO : " + dto.getBno());
	}
	
	@Test
	public void testSelectOne() {
		BoardDTO boardDTO = boardMapper.selectOne(1L);
		
		log.info("boardDTO : " + boardDTO);
	}
	
	@Test
	public void testDelete() {
		int result = boardMapper.remove(1L);
		
		log.info("result : " + result);
	}
	
	@Test
	public void testUpdate() {
		BoardDTO dto = BoardDTO.builder()
				.title("new title")
				.content("new content")
				.delFlag(false)
				.bno(1L)
				.build();
		
		int result = boardMapper.update(dto);
		
		log.info("result : " + result);
	}
	
	@Test
	public void testSelect() {
		
		//List<BoardDTO> list = boardMapper.list();
		//for(BoardDTO dto : list)
		//	log.info(dto);
		
		boardMapper.list().forEach(dto->log.info(dto)); // 람다식
		
		//boardMapper.list().forEach(log::info); // 메서드 참조형식
	}
	
	
	
	
	
	
	@Test
	public void testPaging() {
		
		int page = 3;
		
		//계산식
		int skip = (page-1) * 10;
		int count = 10;
		
		boardMapper.list2(skip, count)
			.forEach(board -> log.info(board));
	}
	
	
	
	
	@Test
	public void testPagNums() {
		
		List<Integer> list = IntStream.rangeClosed(1, 5).boxed().toList();
		log.info(list);
		
		//IntStream.rangeClosed(1, 5).boxed().forEach(i -> log.info(i));
		//IntStream.range() 1부터 5전	정수값 반환
		//IntStream.rangeClosed() 1부터 5까지 정수값 반환
		//boxed()는 객체로 만든다! int(자료형) -> integer(객체)
	}
	
	
}



















