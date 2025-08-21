package org.zerock.club.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@Log4j2
public class SecurityConfig {
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}// end pass..

	@Bean
	public WebSecurityCustomizer webSecurityCustomizer(AuthenticationManagerBuilder auth) throws Exception {
		// 사용자 계정 세팅 user1//패스워드 1111
		auth.inMemoryAuthentication().withUser("user1")
				.password("$2a$10$qbTVRGiC8RePIsMz4z/QP.LjBmLOMGXBCkmW2comzfNaoeidd5/aa").roles("USER");
		// 이미지,JS파일 리소스 사용가능하게함
		return (web) -> web.ignoring().requestMatchers("/static/**");
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		// /samle/all 모든 사용자 가능
		// /sample/member USER 롤 사용자만
		http.authorizeRequests()
		.requestMatchers("/samle/all")
		.permitAll().requestMatchers("/sample/member")
		.hasRole("USER")
		.and().formLogin(); // 인가 인증 문제시 로그인 화면

		// crsf 비활성화
		http.csrf().disable();
		// 로그 아웃 세팅
		http.logout();

		return http.build();

	}
}// end class
