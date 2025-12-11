package org.zerock.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.zerock.dto.BoardDTO;
import org.zerock.dto.BoardListPagingDTO;
import org.zerock.mapper.BoardMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class BoardService {
	
	private final BoardMapper boardMapper;

	public List<BoardDTO> getList(){
		
		return boardMapper.list();
	}
	
	public BoardListPagingDTO getList(int page, int size, String typeStr, String keyword) {
		
		page = (page <= 0) ? 1 : page;
		
		// 10개의 항목 미만이거나, 페이지가 100페이지가 넘어가면 10개씩 보여주기 / 100페이지 전에는 20개 50개씩 보여줘도 상관없다.
		size = (size <= 10 || page > 100) ? 10 : size;
		
		/*
		 *	전체 데이터 100개 있다고 가정.
		 *	1 page : size(10) -> 100 ~ 91, skip = 0
		 *	2 page : size(10) -> 90 ~ 81, skip = 10
		 *	3 page : size(10) -> 80 ~ 71, skip = 20
		 */
		
		int skip = (page - 1) * size;
												// split 분리시킴 -> "TC" => T | C
		String[] types = (typeStr != null) ? typeStr.split("") : null;
		
		List<BoardDTO> list = boardMapper.listSearch(skip, size, types, keyword);
		
		
		int total = boardMapper.listCountSearch(types, keyword);
		
		return new BoardListPagingDTO(list, total, page, size, typeStr, keyword);
	}
	
	
	public Long register(BoardDTO dto) {

	int insertCounter = boardMapper.insert(dto);
	log.info("insertCounter : " + insertCounter);
		
		return dto.getBno();
	}

	
	
	public BoardDTO read(Long bno) {
		
		return boardMapper.selectOne(bno);
	}



	public void remove(Long bno) {
		
		boardMapper.remove(bno);
	}



	public void modify(BoardDTO dto) {
		
		boardMapper.update(dto);
	}
	
}

