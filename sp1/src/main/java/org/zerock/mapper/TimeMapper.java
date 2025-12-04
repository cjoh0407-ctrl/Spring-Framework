package org.zerock.mapper;

import org.apache.ibatis.annotations.Select;

public interface TimeMapper {
	
	//--> 조회 어노테이션 방식
	@Select("select now()")
	String getTime();
	
	String getTime2();
}
