package sec01.ex01;
import java.io.IOException;
//javax 에서 jakarta  로 변경
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
//URL patterns.
@WebServlet("/first") //HttpServlet 상속
public class FirstServlet extends HttpServlet {
	// 클래스 직렬화
	private static final long serialVersionUID = 1L;
	public void init() throws ServletException {
		System.out.println("init 메서드 호출");
	}
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("doGet 메서드 호출");
	}
	public void destroy() {
		System.out.println("destroy 메서드 호출");
	}
}



