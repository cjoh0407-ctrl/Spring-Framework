package org.zerock.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.dto.ReplyDTO;
import org.zerock.dto.ReplyListPagingDTO;
import org.zerock.dto.SampleDTO;
import org.zerock.service.ReplyService;
import org.zerock.service.exception.ReplyException;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequiredArgsConstructor
@Log4j2             
@RequestMapping("/replies")
public class ReplyController {
	
	private final ReplyService replyService;	
		
	@ExceptionHandler(ReplyException.class)
	public ResponseEntity<String> handleReplyError(ReplyException ex){
		log.info("---------------------handleReplyError--------------------------------");
		log.error(ex.getMessage());
		return ResponseEntity.status(ex.getCode()).body(ex.getMsg());
	}

	@PostMapping("")
	public ResponseEntity<Map<String, Integer>> add(@RequestBody ReplyDTO replyDTO){
		
		log.info("---------------add-----------------");
		log.info(replyDTO);
		
		replyService.add(replyDTO);
		
		return ResponseEntity.ok(Map.of("result", replyDTO.getRno()));
	}
	
	//localhost:8080/replies/{bno}/list?page=2&size=10
	@GetMapping("/{bno}/list")
	public ResponseEntity<ReplyListPagingDTO> listOfBoard(
			@PathVariable("bno") Long bno,
			@RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "size", defaultValue = "10") int size
			){
		log.info("=========================page,size========================");
		log.info(page);
		log.info(size);
		
		ReplyListPagingDTO listOfBoard =
				replyService.listOfBoard(bno, page, size);
		
		//java 객체 -> json 반환 -> jackson 라이브러리가 처리
		return ResponseEntity.ok(listOfBoard);
	}
	
	
	//localhost:8080/replies/10 + method : get
	@GetMapping("/{rno}")
	public ResponseEntity<ReplyDTO> read(@PathVariable("rno") int rno){
		return ResponseEntity.ok(replyService.getOne(rno));
	}
	
	
	//localhost:8080/replies/10 + method : delete
	@DeleteMapping("/{rno}")
	public ResponseEntity<Map<String, String>> delete(@PathVariable("rno") int rno){
		
		replyService.remove(rno);
		
		return ResponseEntity.ok(Map.of("result", "deleted"));
	}
	
	//localhost:8080/replies/10 + method : put
	//@RequestMapping(method = {RequestMethod.PATCH, RequestMethod.PUT})	//put 전부 다, patch 일부분
	@PutMapping("/{rno}")
	public ResponseEntity<Map<String, String>> modify(@PathVariable("rno") int rno,
			ReplyDTO replyDTO){
		
		log.info("rno : " + rno);
		log.info("replyDTO : " + replyDTO);
		
		//replyDTO.setRno(rno);
		
		replyService.modify(replyDTO);
		
		return ResponseEntity.ok(Map.of("result", "modified"));
	}
}



















