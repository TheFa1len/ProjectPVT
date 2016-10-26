package Servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.CarparkDAO;
import DAO.PostDAO;
import Model.Carpark;
import Model.Post;

@WebServlet(name = "ToRegistration", urlPatterns = "/ToRegistration")
public class ToRegistration extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8583568643828829507L;

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<Carpark> cars = new ArrayList<Carpark>();
		cars = new CarparkDAO().findAll();
		request.setAttribute("cars", cars);
		List<Post> posts = new ArrayList<Post>();
		posts = new PostDAO().findAll();
		request.setAttribute("posts", posts);
		RequestDispatcher rd = request.getRequestDispatcher("registration.jsp");
		rd.forward(request, response);
	}
}
