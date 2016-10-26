package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.AplicationDAO;
import DAO.CarparkDAO;
import DAO.EmployeesDAO;
import DAO.VoyageDAO;
import Model.Aplication;
import Model.Carpark;
import Model.Empployees;
import Model.Voyage;

@WebServlet(name = "CreateVoyage", urlPatterns = "/CreateVoyage")
public class CreateVoyage extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6708139366298553534L;

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text / html;charset=UTF-8");
		PrintWriter pw = response.getWriter();
		
		if (request.getParameter("app") == null) {
			List<Aplication> resultAdmin = new ArrayList<Aplication>();
			resultAdmin = new AplicationDAO().findAll();
			request.setAttribute("result_admin", resultAdmin);
			RequestDispatcher rd = request
					.getRequestDispatcher("aplication.jsp");
			rd.forward(request, response);
		} else {
			int id = Integer.parseInt(request.getParameter("app"));
			AplicationDAO daoA = new AplicationDAO();
			Aplication app = daoA.findEntityById(id);
			Voyage v = new Voyage();
			String duration = request.getParameter("duration");
			v.setDuration(Integer.parseInt(duration));
			java.util.Date date = Calendar.getInstance().getTime();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			v.setStartDate(sdf.format(date).toString());
			v.setStatement(0);
			v.setId(id);
			List<Carpark> carpark = new ArrayList<Carpark>();
			carpark = new CarparkDAO().findAll();
			List<Aplication> apps = new ArrayList<Aplication>();
			apps = daoA.findFree();
			request.setAttribute("apps", apps);
			int id_auto = Integer.parseInt(request.getParameter("cars"));
			Empployees driver = new Empployees();
			driver = new EmployeesDAO().findEntityByCarId(id_auto);
			v.setEmployee_id(driver.getId());
			Carpark car = new CarparkDAO().findEntityById(driver.getAutoId());
			if (app.getCargo() > car.getCargo()
					&& app.getNumberOfPassengers() > car.getPassengers()
					&& car.getStatement_id() == 3) {
				apps = daoA.findFree();
				carpark = new CarparkDAO().findAll();
				request.setAttribute("cars", carpark);
				request.setAttribute("apps", apps);
				pw.print("<p style=\"color:red\">This car cant handle this task</p>");
				RequestDispatcher rd = request
						.getRequestDispatcher("createvoyage.jsp");
				rd.include(request, response);
			} else {
				new VoyageDAO().create(v);
				app.setVoyage_id(id);
				daoA.update(app);
				List<Aplication> resultAdmin = new ArrayList<Aplication>();
				resultAdmin = new AplicationDAO().findAll();
				request.setAttribute("result_admin", resultAdmin);
				RequestDispatcher rd = request
						.getRequestDispatcher("aplication.jsp");
				rd.forward(request, response);
			}
		}

	}
}