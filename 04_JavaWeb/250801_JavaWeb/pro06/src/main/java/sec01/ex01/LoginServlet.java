package sec01.ex01;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//web.xml 에 등록 대신
@WebServlet("/login") 
public class LoginServlet  extends HttpServlet{
   
	public void init() throws ServletException {
      System.out.println("init 메서드 호출");
   }//end init..


	//웹브라우저에서 전송한 정보가 톰캣컨테이너가
	//HttpServletRequest 객체 생성 후 넘겨줌
   protected void doGet(HttpServletRequest request , HttpServletResponse response) 
                                            throws ServletException, IOException {
      // 한국어 처리
	  request.setCharacterEncoding("utf-8"); 
	  
	  ////HTML input 태그 name
	  //getParameter 이용
      String user_id=request.getParameter("user_id");
      String user_pw=request.getParameter("user_pw"); 
      
      //브라우저에 출력
      PrintWriter out = response.getWriter();	
      
      //브라우저에 응답할 데이터를 종류 및 인코딩 설정
      response.setContentType("text/html;charset=utf-8"); 
      
      System.out.println("아이디:"+user_id);
      System.out.println("비밀번호:"+user_pw);
      if(user_id!= null &&(user_id.length()!=0)){
  		out.print("<html>");  
  		out.print("<body>");
  		out.print( user_id +" 님!! 로그인 하셨습니다." );
  		out.print("</html>");
  		out.print("</body>");
        }else{
  		out.print("<html>");  
  		out.print("<body>");
  		out.print("아이디를 입력하세요!!!" ) ;
  		out.print("<br>");
  		out.print("<a href='http://localhost:8080/pro06/test01/login.html'>로그인창으로 이동  </a>");
  		out.print("</html>");
  		out.print("</body>");
        }      
      
   }

   public void destroy() {
      System.out.println("destroy 메서드 호출");
   }
}
