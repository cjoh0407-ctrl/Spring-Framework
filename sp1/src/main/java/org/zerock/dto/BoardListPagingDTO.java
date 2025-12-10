package org.zerock.dto;

import java.util.List;
import java.util.stream.IntStream;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@ToString
@Getter
@Setter
public class BoardListPagingDTO {
	
	private List<BoardDTO> boardDTOList;	// 전체 목록
	
	private int totalCount;	// 전체 갯수
	
	private int page, size;	// 페이지 번호, 화면당 보여지는 갯수
	
	private int start, end;	// 페이지 시작, 끝
	
	private boolean prev, next; // 이전, 다음 버튼
	
	private List<Integer> pageNums; // 122개라고 가정했을때 화면상 1~10, 11~13까지 있어야함.
	
	// 왜 롬복 자동 생성자를 안쓰지???????????
	public BoardListPagingDTO(List<BoardDTO> boardDTOList, int totalCount, int page, int size) {
		this.boardDTOList = boardDTOList;
		this.totalCount = totalCount;
		this.page = page;
		this.size = size;
		
		//start 계산을 위한 end페이지
		int tempEnd = (int)(Math.ceil(page/10.0)) * 10;
								//ceil 무조건 올림계산. 1.2면 2.0으로
		
		this.start = tempEnd - 9;
		
		// 275면 28번까지 나와야하는데 30까지 나오는걸 제대로 나오게 수정.
		if((tempEnd * size) > totalCount) {
			this.end = (int)(Math.ceil(totalCount / (double)size));
		}else {
			this.end = tempEnd;
		}
		
		//prev, next 계산
		this.prev = (start != 1); // 1들어오면 false 11, 21은 true
		this.next = (totalCount > (this.end * size)); // 275면 10, 20일때는 true, 28일때는 false
		
		this.pageNums = IntStream.rangeClosed(start, end).boxed().toList();
	
	}
}
