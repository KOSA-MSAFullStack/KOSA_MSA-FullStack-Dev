package sec01.ex01;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//web.xml 에 등록 대신
//서블릿 매핑 이름
@WebServlet("/input")
public class InputServlet  extends HttpServlet{
	
   public void init() throws ServletException {
      System.out.println("init 메서드 호출");
   }//end class

   //
   protected void doGet(HttpServletRequest request, HttpServletResponse response) 
                                            throws ServletException, IOException {
       //한국어 처리       
	   request.setCharacterEncoding("utf-8");
	   
	   //HTML input 태그 name
      String user_id=request.getParameter("user_id");
      String user_pw=request.getParameter("user_pw"); 
      System.out.println("아이디:"+user_id);
      System.out.println("비밀번호:"+user_pw);
      
      //input type="checkbox" name=subject
      String[] subject=request.getParameterValues("subject"); 
      for(String str:subject){
         System.out.println("선택한 과목:"+str);
      }//end for    
      
   }//end do..

   public void destroy() {
      System.out.println("destroy 메서드 호출");
   }//end de..
}//end class
