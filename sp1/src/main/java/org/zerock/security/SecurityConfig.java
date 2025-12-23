package org.zerock.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import lombok.extern.log4j.Log4j2;

@Configuration
@Log4j2
@EnableWebSecurity // 보안설정
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		
		log.info("------------filterChain-----------");
		
		http.formLogin(config->{ // 로그인 폼 설정
			
		});
		
		return http.build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() { //패스워드를 암호화해줌
		return new BCryptPasswordEncoder();
	}
}

