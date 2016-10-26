package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.CarparkDAO;
import DAO.StatementsDAO;
import DAO.VoyageDAO;
import Model.Carpark;
import Model.Empployees;
import Model.Statements;
import Model.Voyage;

@WebServlet(name = "UpdateVoyage", urlPatterns = "/UpdateVoyage")
public class UpdateVoyage extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3624690051882401219L;

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text / html;charset=UTF-8");
		PrintWriter pw = response.getWriter();
		if (Integer.parseInt(request.getParameter("app")) == 0) {
			VoyageDAO daoV = new VoyageDAO();
			CarparkDAO daoC = new CarparkDAO();
			Carpark carDriver = new Carpark();
			HttpSession session = request.getSession(false);
			Empployees temp = (Empployees) session.getAttribute("driver");
			carDriver = daoC.findEntityById(temp.getAutoId());
			List<Statements> statements = new ArrayList<Statements>();
			statements = new StatementsDAO().findAll();
			request.setAttribute("statements", statements);
			request.setAttribute("car_driver", carDriver);
			List<Voyage> resultDriver = new ArrayList<Voyage>();
			resultDriver = daoV.findAllByEmp(temp.getId());
			request.setAttribute("result_driver", resultDriver);
			RequestDispatcher rd = request.getRequestDispatcher("voyages.jsp");
			rd.forward(request, response);
		} else {
			int id = Integer.parseInt(request.getParameter("app"));
			VoyageDAO daoV = new VoyageDAO();
			Voyage voy = daoV.findEntityById(id);
			voy.setStatement(1);
			daoV.update(voy);
			CarparkDAO daoC = new CarparkDAO();
			Carpark carDriver = new Carpark();
			HttpSession session = request.getSession(false);
			Empployees temp = (Empployees) session.getAttribute("driver");
			carDriver = daoC.findEntityById(temp.getAutoId());
			List<Statements> statements = new ArrayList<Statements>();
			statements = new StatementsDAO().findAll();
			request.setAttribute("statements", statements);
			request.setAttribute("car_driver", carDriver);
			List<Voyage> resultDriver = new ArrayList<Voyage>();
			resultDriver = daoV.findAllByEmp(temp.getId());
			request.setAttribute("result_driver", resultDriver);
			pw.print("<p style=\"color:green\">Voyage was updated</p>");
			RequestDispatcher rd = request.getRequestDispatcher("voyages.jsp");
			rd.include(request, response);
		}
	}

}
