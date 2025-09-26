package com.bharath.springai.services;

import java.util.List;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OpenAiService {

    // Spring AI에서 제공하는 EmbeddingModel 구현체를 주입
    @Autowired
    private EmbeddingModel embeddingModel;

    /**
     * 두 텍스트 입력의 임베딩 사이의 코사인 유사도를 계산합니다.
     * 
     * @param text1 첫 번째 텍스트 문자열
     * @param text2 두 번째 텍스트 문자열
     * @return 두 임베딩 벡터 사이의 코사인 유사도 점수
     */
    public double findSimilarity(String text1, String text2) {
        // 입력 텍스트들에 대한 임베딩 벡터 생성
        List<float[]> response = embeddingModel.embed(List.of(text1, text2));
        // 두 벡터 사이의 코사인 유사도를 계산하여 반환
        return cosineSimilarity(response.get(0), response.get(1));
    }

    /**
     * 두 개의 float 벡터 사이의 코사인 유사도를 계산합니다.
     *
     * @param vectorA 첫 번째 벡터
     * @param vectorB 두 번째 벡터
     * @return 코사인 유사도 값
     */
    private double cosineSimilarity(float[] vectorA, float[] vectorB) {
        // 두 벡터의 길이가 동일한지 확인
        if (vectorA.length != vectorB.length) {
            throw new IllegalArgumentException("Vectors must be of the same length");
        }

        // 내적과 두 벡터의 크기를 위한 변수 초기화
        double dotProduct = 0.0;
        double magnitudeA = 0.0;
        double magnitudeB = 0.0;

        // 두 벡터의 내적과 크기를 계산
        for (int i = 0; i < vectorA.length; i++) {
            dotProduct += vectorA[i] * vectorB[i];
            magnitudeA += vectorA[i] * vectorA[i];
            magnitudeB += vectorB[i] * vectorB[i];
        }

        // 코사인 유사도 값을 반환
        return dotProduct / (Math.sqrt(magnitudeA) * Math.sqrt(magnitudeB));
    }

} // 클래스 끝
