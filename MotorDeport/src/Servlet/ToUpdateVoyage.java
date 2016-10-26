package Servlet;
import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.VoyageDAO;
import Model.Empployees;
import Model.Voyage;
@WebServlet(name="ToUpdateVoyage", urlPatterns="/ToUpdateVoyage")
public class ToUpdateVoyage extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6260063917564504495L;

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text / html;charset=UTF-8");
		HttpSession session = request.getSession(false);
		Empployees user = (Empployees) session.getAttribute("driver");
		VoyageDAO daoV = new VoyageDAO();
		List<Voyage> result = daoV.findAllNonByEmp(user.getId());
		request.setAttribute("result_voy",result);
		RequestDispatcher rd = request.getRequestDispatcher("updatevoyage.jsp");
		rd.include(request, response);
	}
}
