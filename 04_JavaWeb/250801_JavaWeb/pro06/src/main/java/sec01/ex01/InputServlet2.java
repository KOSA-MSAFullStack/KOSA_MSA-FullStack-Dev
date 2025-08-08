package sec01.ex01;

import java.io.IOException;
import java.util.Enumeration;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//web.xml 에 등록 대신
@WebServlet("/input2")
public class InputServlet2 extends HttpServlet {

	public void init() throws ServletException {
		System.out.println("init 메서드 호출");
	}// end init..

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 한국어 처리
		request.setCharacterEncoding("utf-8");

		// generates a series of elements
		// 전체 이름 다 가져옴
		Enumeration<String> enu = request.getParameterNames();
		// 각요소가 있는지 확인
		while (enu.hasMoreElements()) {
			// 요소에서 이름 추출 후 저장
			String name = (String) enu.nextElement();
			System.out.println(name);
			//HTML에서 요소 가져오기
			String[] values = request.getParameterValues(name);
			for (String value : values) {
				System.out.println("name=" + name + ",value=" + value);
			} // end for
		} // end while
	}// end

	public void destroy() {
		System.out.println("destroy 메서드 호출");
	}// end des..
}// end class
