package org.zerock.security;

import java.io.IOException;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.rmi.ServerException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class Custom403handler implements AccessDeniedHandler{

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServerException{
		log.info("----------------------accessDeniedException----------------------------");
	
		log.info("path : " + request.getContextPath());
		
		response.sendRedirect(request.getContextPath() + "/sample/access-denied");
	}
	
	
}
