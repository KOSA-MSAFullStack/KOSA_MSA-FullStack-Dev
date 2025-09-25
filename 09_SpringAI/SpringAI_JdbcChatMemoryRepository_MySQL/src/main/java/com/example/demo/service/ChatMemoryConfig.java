package com.example.demo.service; // 패키지 선언

import org.springframework.ai.chat.memory.repository.jdbc.JdbcChatMemoryRepository; // JDBC 기반 메모리 저장소 임포트
import org.springframework.ai.chat.memory.repository.jdbc.MysqlChatMemoryRepositoryDialect;
import org.springframework.context.annotation.Bean; // Bean 등록 애노테이션 임포트
import org.springframework.context.annotation.Configuration; // 구성 클래스 애노테이션 임포트
import javax.sql.DataSource; // 데이터소스 타입 임포트

@Configuration // 스프링 구성 클래스임을 선언
public class ChatMemoryConfig { // 구성 클래스 시작

    @Bean // 스프링 컨테이너에 Bean으로 등록
    public JdbcChatMemoryRepository jdbcChatMemoryRepository(DataSource dataSource) { // DataSource를 주입받아 리포지토리 Bean 생성
        return JdbcChatMemoryRepository // 리포지토리 빌더 진입
                .builder().dataSource(dataSource) // 빌더에 데이터소스 설정
                .dialect(new MysqlChatMemoryRepositoryDialect()) // MySQL 방언 지정
                .build();
    } // 메서드 끝
} // 클래스 끝
