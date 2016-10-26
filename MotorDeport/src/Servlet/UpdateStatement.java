
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
@WebServlet(name="UpdateStatement", urlPatterns="/UpdateStatement")
public class UpdateStatement extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7837840757510100341L;

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text / html;charset=UTF-8");
		CarparkDAO daoC = new CarparkDAO();
		PrintWriter pw = response.getWriter();
		HttpSession session = request.getSession(false);
		Empployees driver = (Empployees) session.getAttribute("driver");
		Carpark car = daoC.findEntityById(driver.getAutoId());
		System.out.println("findByEntity");
		car.setStatement_id(Integer.parseInt(request.getParameter("state")));
		daoC.update(car);
		System.out.println("fupdate");
		Carpark carDriver = new Carpark();
		carDriver = daoC.findEntityById(driver.getAutoId());
		System.out.println("findByEntity2");
		List<Statements> statements = new ArrayList<Statements>();
		statements = new StatementsDAO().findAll();
		System.out.println("findAll");
		request.setAttribute("statements", statements);
		request.setAttribute("car_driver", carDriver);
		List<Voyage> resultDriver = new ArrayList<Voyage>();
		resultDriver = new VoyageDAO().findAllByEmp(driver.getId());
		System.out.println("findAllDrivers");
		request.setAttribute("result_driver", resultDriver);
		pw.print("<p style=\"color:green\">Statement was updated</p>");
		RequestDispatcher rd = request.getRequestDispatcher("voyages.jsp");
		rd.include(request, response);
	}

}
