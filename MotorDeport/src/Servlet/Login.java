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

import DAO.AplicationDAO;
import DAO.CarparkDAO;
import DAO.EmployeesDAO;
import DAO.PostDAO;
import DAO.StatementsDAO;
import DAO.VoyageDAO;
import Model.Aplication;
import Model.Carpark;
import Model.Empployees;
import Model.Post;
import Model.Statements;
import Model.Voyage;

@WebServlet(name = "Login", urlPatterns = "/Login")
public class Login extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3654876251516837609L;

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text / html;charset=UTF-8");
		PrintWriter pw = response.getWriter();

		String n = request.getParameter("username");
		String p = request.getParameter("userpass");
		HttpSession session = request.getSession(false);
		List<Carpark> cars = new ArrayList<Carpark>();
		cars = new CarparkDAO().findAll();
		request.setAttribute("cars", cars);
		List<Post> posts = new ArrayList<Post>();
		posts = new PostDAO().findAll();
		session.setAttribute("login", n);
		request.setAttribute("posts", posts);
		EmployeesDAO dao = new EmployeesDAO();
		Empployees temp = new Empployees();
		temp = dao.findByLogin(n);
		if (temp != null) {
			session.setAttribute("name", temp.getName());
			request.setAttribute("name", temp.getName());
			if (temp.getLogin().equals(n) && temp.getPassword().equals(p) && !(temp == null)) {
				if (temp.getPost_id() == 2) {
					session.setAttribute("driver", temp);
					CarparkDAO daoC = new CarparkDAO();
					Carpark carDriver = new Carpark();
					VoyageDAO daoV = new VoyageDAO();
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

				}
				if (temp.getPost_id() == 1) {
					List<Aplication> resultAdmin = new ArrayList<Aplication>();
					resultAdmin = new AplicationDAO().findAll();
					request.setAttribute("result_admin", resultAdmin);
					RequestDispatcher rd = request.getRequestDispatcher("aplication.jsp");
					rd.forward(request, response);
				}
			} else {
				pw.print("<p style=\"color:red\">Sorry username or password error</p>");
				RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
				rd.include(request, response);
			}
		}

	}
}