package lab3;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class LoginServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//doc gia tri cookie
		String username = CookieUtils.get("username", req);
		String password = CookieUtils.get("password", req);
		
		//chuyen sang login.jsp de hien thi len form
		req.setAttribute("username", username);
		req.setAttribute("password", password);
		
		req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// doc tham so form dang nhap
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String remember = req.getParameter("remember");
		
		//kiem tra tai khoan dang nhap
		if (!username.equalsIgnoreCase("poly")) {
			req.setAttribute("message", "sai ten dnag nhap!");
		}else if(password.length() < 6){
			req.setAttribute("message", "sai mat khau!");
		}else {
			req.setAttribute("message", "dang nhap thanh cong!");
			//ghi nho hoac xoa tai khoan da ghi bang cookie
			int hours = (remember == null)?0:30*24; //0=xoa
			CookieUtils.add("username", username, hours, resp);
			CookieUtils.add("password", password, hours, resp);
		}
		
		req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
	}

}
