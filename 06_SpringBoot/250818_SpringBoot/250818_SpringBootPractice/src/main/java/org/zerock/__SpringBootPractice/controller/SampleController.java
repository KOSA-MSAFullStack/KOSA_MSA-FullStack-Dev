// SampleController.java

package org.zerock.__SpringBootPractice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

    // 기존 /hello 메서드
    @GetMapping("/hello")
    public String[] hello(){
        return new String[]{"hello","world"}; // "wolrd" 오타 수정
    }

    // 새로 추가한 루트 경로 메서드
    @GetMapping("/")
    public String mainPage() {
        return "Welcome to the Main Page!";
    }
}