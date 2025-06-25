package ch9_1_FunctionalInterfaces;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
public class Consumers_Exam {
   public static void main(String[] args) {
       // 리스트 생성
       List<String> names = Arrays.asList("John", "Freddy", "Samuel");
       // Consumer<T> 정의
       Consumer<String> printGreeting = name -> System.out.println("Hello, " + name);
       // Consumer<T> 사용하여 각 이름에 대해 출력
       names.forEach(printGreeting);
       //람다식
       System.out.println("---lambda----");
       names.forEach(name -> System.out.println("Hello, " + name));
   }
}
