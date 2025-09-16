package org.zerock.club.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.zerock.club.security.handler.ClubLoginSuccessHandler;
import org.zerock.club.security.service.ClubOAuth2UserDetailsService;
import org.zerock.club.security.service.ClubUserDetailsService;
import org.zerock.club.security.service.OAuth2MemberService;

@Configuration
@Log4j2
public class SecurityConfig {
	// 순서 중요
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}// end pass..
		// ClubLoginSuccessHandler 등록
		// ClubLoginSuccessHandler 등록

	@Bean
	public ClubLoginSuccessHandler successHandler() {
		return new ClubLoginSuccessHandler(passwordEncoder());
	}// end CLu..

	@Autowired
	@Lazy
	private ClubOAuth2UserDetailsService clubOAuth2UserDetailsService;

	@Autowired
	private OAuth2MemberService oAuth2MemberService;
	
   @Autowired
   private ClubUserDetailsService clubUserDetailsService;

	// 권한 계층 설정을 위한 Bean 등록
	@Bean
	public RoleHierarchyImpl roleHierarchy() {
		RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
		// 'ROLE_ADMIN'은 'ROLE_USER'의 권한도 포함한다는 설정
		roleHierarchy.setHierarchy("ROLE_ADMIN > ROLE_MANAGER > ROLE_USER");
		return roleHierarchy;
	}

	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() throws Exception {
		// 이미지,JS파일 리소스 사용가능하게함
		return (web) -> web.ignoring().requestMatchers("/static/**");
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		// /samle/all 모든 사용자 가능
		// /sample/member USER 롤 사용자만
		/*
		 * http.authorizeRequests().requestMatchers("/samle/all").permitAll().
		 * requestMatchers("/sample/member") .hasRole("USER").and().formLogin(); // 인가
		 * 인증 문제시 로그인 화면
		 */		
		
		http.authorizeRequests()
         .requestMatchers("/sample/member").hasRole("USER")
         .and().formLogin(); //인가 인증 문제시 로그인 화면

		// crsf 비활성화
		http.csrf().disable();
		// 로그 아웃 세팅
		http.logout();

		// 구글 oauth 인증 추가
		http.oauth2Login().userInfoEndpoint()// 로그인 완료 후 회원 정보 받기
				.userService(clubOAuth2UserDetailsService) // 서비스 변경
				.and().successHandler(successHandler()); // 핸들러 등록
		
       //일반 from 로그인 rememberMe 설정
       http.rememberMe()  //7day
               .tokenValiditySeconds( 60 * 60 * 24 * 7)
               .userDetailsService(clubUserDetailsService);


		return http.build();
	}
}// end class
