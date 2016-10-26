package Servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.EmployeesDAO;
import Model.Empployees;

@WebServlet(name = "Registration", urlPatterns = "/Registration")
public class Registration extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2957563344266743241L;

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text / html;charset=UTF-8");
		String name = request.getParameter("name");
		int phone = Integer.parseInt(request.getParameter("phone"));
		String lastName = request.getParameter("lastName");
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		int auto_id = Integer.parseInt(request.getParameter("car"));
		int post_id = Integer.parseInt(request.getParameter("post"));
		Empployees item = new Empployees();
		item.setId(1);
		item.setName(name);
		item.setLastname(lastName);
		item.setPhone(phone);
		item.setLogin(login);
		item.setPassword(password);
		if(post_id==1){
			item.setAutoId(0);
			}
		else{
		item.setAutoId(auto_id);
		}
		item.setPost_id(post_id);
		new EmployeesDAO().create(item);
		RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
		rd.forward(request, response);
	}
}
