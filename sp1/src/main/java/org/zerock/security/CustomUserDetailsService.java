package org.zerock.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.zerock.dto.AccountDTO;
import org.zerock.dto.AccountRole;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		// 사용자의 이름을 불러와라. 유저이름을 찾지 못하면 오류 내버려!
		
		log.info("------------------loadUserByUsername--------------------");
		log.info("username : " + username);
		
		/*
		UserDetails user = User.builder()
				.username(username)
				.password("{noop}1111") -> {noop} 비밀번호 암호화 XXXX
				.roles("USER")
				.build();
		*/
		
//		UserDetails user = User.builder()
//				.username(username)	//ID
//				.password(encoder.encode("1111"))// 비밀번호 암호화해서 저장
//				.roles("USER")	//user라는 역할 (권한)
//				.build();
		
		AccountDTO accountDTO = new AccountDTO();
		accountDTO.setUid(username);
		accountDTO.setUpw(encoder.encode("1111"));
		accountDTO.addRole(AccountRole.USER);
		accountDTO.addRole(AccountRole.MANAGER);
		
		return accountDTO;
	}

}
