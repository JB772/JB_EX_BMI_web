package web;

import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		try (PrintWriter pw = response.getWriter()){
			pw.append("我成功啦～～")
				.append(request.getContextPath());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
