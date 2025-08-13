package com.mycompany.webapp.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:aws.properties")
public class AwsS3Config {

    // AmazonS3 클라이언트 빈을 생성
    @Bean
    public AmazonS3 amazonS3(Environment env) {
        // aws.properties에서 키 값 불러오기
        String accessKey = env.getProperty("aws.accessKey");
        String secretKey = env.getProperty("aws.secretKey");
        String region = env.getProperty("aws.region");

        // accessKey 또는 secretKey가 없으면 예외 발생
        if (accessKey == null || secretKey == null) {
            throw new IllegalArgumentException("AWS accessKey or secretKey is null");
        }

        // 인증 자격 증명 생성
        BasicAWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);

        // AmazonS3 클라이언트 구성 및 반환
        return AmazonS3ClientBuilder.standard()
                .withRegion(region)
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .build();
    }

    // 프로퍼티 파일을 읽을 수 있게 해주는 빈 등록
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
