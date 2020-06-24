package admin;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/admin/*")
public class adminController extends HttpServlet {
	adminDAO adDAO;
	
	public void init(ServletConfig config) throws ServletException {
		adDAO = new adminDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doHandle(request, response);

	}

	protected void doHandle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nextPage = null;
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String action = request.getPathInfo();
		System.out.println("action:" + action);
		
		if(action.equals("/admin.do")) {
			nextPage = "/admin/admin.jsp";
		}else if(action.equals("/adminPro.do")) {
			String id = request.getParameter("id");
			int num = Integer.parseInt(request.getParameter("num"));
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			Date date = request.getParameter("date");
			String pos = request.getParameter("pos");
			String dept = request.getParameter("dept");
			adminVO adVO = new adminVO(id, num, title, content, date, pos, dept);
		}
	}
}
