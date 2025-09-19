package org.zerock.club.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.zerock.club.security.handler.ClubLoginSuccessHandler;
import org.zerock.club.security.service.ClubOAuth2UserDetailsService;
import org.zerock.club.security.service.ClubUserDetailsService;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@Log4j2
public class SecurityConfig {

    /** 비밀번호 암호화 Bean */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /** 로그인 성공 핸들러 Bean */
    @Bean
    public ClubLoginSuccessHandler successHandler() {
        return new ClubLoginSuccessHandler(passwordEncoder());
    }

    @Autowired
    @Lazy
    private ClubOAuth2UserDetailsService clubOAuth2UserDetailsService;

    @Autowired
    private ClubUserDetailsService clubUserDetailsService;

    /** 권한 계층 구조 설정 (최신 방식) */
    @Bean
    public RoleHierarchyImpl roleHierarchy() {
        return RoleHierarchyImpl.fromHierarchy("""
            ROLE_ADMIN > ROLE_MANAGER
            ROLE_MANAGER > ROLE_USER
        """);
    }

    /** 정적 리소스 보안 필터 제외 */
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/static/**");
    }

    /** Spring Security 필터 체인 설정 */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            // 인가(Authorization) 설정
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/sample/all").permitAll()          // 누구나 접근 가능
                .requestMatchers("/sample/member").hasRole("USER")   // USER 권한 필요
                .anyRequest().authenticated()                       // 나머지는 인증 필요
            )

            // 로그인 설정 (기본 로그인 폼)
            .formLogin(withDefaults())

            // CSRF 비활성화
            .csrf(csrf -> csrf.disable())

            // 로그아웃 설정
            .logout(withDefaults())

            // OAuth2 로그인 설정
            .oauth2Login(oauth2 -> oauth2
                .userInfoEndpoint(userInfo -> 
                    userInfo.userService(clubOAuth2UserDetailsService) // OAuth2 사용자 정보 서비스
                )
                .successHandler(successHandler()) // 로그인 성공 핸들러
            )

            // Remember-Me 설정 (7일간 유지)
            .rememberMe(remember -> remember
                .tokenValiditySeconds(60 * 60 * 24 * 7)
                .userDetailsService(clubUserDetailsService)
            );

        return http.build();
    }
}
