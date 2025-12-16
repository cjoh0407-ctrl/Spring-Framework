package org.zerock.mapper;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.zerock.dto.ReplyDTO;

import lombok.extern.log4j.Log4j2;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j2
class ReplyMapperTest {

	@Autowired
	private ReplyMapper replyMapper;
	
	@Test
	public void testInsert() {
		
		ReplyDTO replyDTO = ReplyDTO.builder()
				.bno(54252L)
				.replyText("댓글 내용")
				.replyer("홍길동")
				.build(); 
		
		int result = replyMapper.insert(replyDTO);
		log.info("result : " + result);
		log.info("rno : " + replyDTO.getRno());
	}
	
	@Test
	public void testRead() {
		ReplyDTO dto = replyMapper.read(4);
		log.info("dto : " + dto);
	}
	
	@Test
	public void testDelete() {
		
		log.info("result : " + replyMapper.delete(3));
	}
	
	@Test
	public void testUpdate() {
		
		ReplyDTO dto = ReplyDTO.builder()
					.replyText("수정 댓글")
					.rno(1)
					.build();
		
		replyMapper.update(dto);
		
		log.info(replyMapper.read(1));
	}

	@Test
	public void testInserts() {
		long[] bnos = {54252L, 54251L, 54250L};
		
		for(Long bno : bnos) {
			for(int i=0; i<100; i++) {
				ReplyDTO dto = ReplyDTO.builder()
						.bno(bno)
						.replyText("replyer" + i)
						.replyer("replyer" + i)
						.build();
				
				replyMapper.insert(dto);
			}
		}
	}
	
	@Test
	public void testList() {
		replyMapper.listOfBoard(54252L, 10, 10).forEach(reply -> log.info("reply : " + reply));
	}
	
	
	
	
}











