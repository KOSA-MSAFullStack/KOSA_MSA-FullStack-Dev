package sec02.ex01;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet2
 */
@WebServlet("/login2")
public class LoginServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void init() throws ServletException {
		System.out.println("init 메서드 호출");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//한국어 처리
		request.setCharacterEncoding("utf-8");
		//출력시 한국어 처리
		response.setContentType("text/html;charset=utf-8");
		//브라우져에 출력 세팅
		PrintWriter out = response.getWriter();

		//html에서 데이터 가져오기
		String id = request.getParameter("user_id");
		String pw = request.getParameter("user_pw");

		//HTML 저장
		String data = "<html>";
		data += "<body>";
		data += "아이디 : " + id;
		data += "<br>";
		data += "비밀번호 : " + pw;
		data += "</html>";
		data += "</body>";
		out.print(data);
	}//end  doGet

	public void destroy() {
		System.out.println("destroy 메서드 호출");
	}//end des..

}//end class
