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
import DAO.AplicationDAO;
import DAO.CarparkDAO;
import Model.Aplication;
import Model.Carpark;

@WebServlet(name = "ToCreateVoyage", urlPatterns="/ToCreateVoyage")
public class ToCreateVoyage extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6505487071233852275L;

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		AplicationDAO daoA = new AplicationDAO();
		List<Aplication> apps = new ArrayList<Aplication>();
		apps=daoA.findFree();
		List<Carpark> carpark = new ArrayList<Carpark>();
		carpark = new CarparkDAO().findAll();
		request.setAttribute("cars",carpark);
		request.setAttribute("apps",apps);
		RequestDispatcher rd = request.getRequestDispatcher("createvoyage.jsp");
		rd.forward(request, response);
	}

}