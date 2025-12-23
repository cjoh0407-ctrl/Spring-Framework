package org.zerock.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * use springdb;

create table tbl_reply(
   rno int auto_increment primary key,
    replyText varchar(500) not null, -- 댓글 내용
    replyer varchar(50) not null,  -- 작성자
    replydate timestamp default now(),
    updatedate timestamp default now(),
    delflag boolean default false,
    bno int not  null  
);

alter table tbl_reply add constraint fk_reply_board foreign key(bno)
references tbl_board(bno);

create index idx_reply_board on tbl_reply(bno desc, rno asc);
 */


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReplyDTO {
	private int rno;
	private String replyText;
	private String replyer;
	
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDateTime replyDate;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDateTime updateDate;
	
	private boolean delflag;
	
	private Long bno;
}
