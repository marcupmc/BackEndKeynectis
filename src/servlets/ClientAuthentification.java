package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ControllerAuthentification;
import dao.DAOUtilisateur;
import domain.Utilisateur;

/**
 * Servlet implementation class ClientAuthentification
 */
@WebServlet("/ClientAuthentification")
public class ClientAuthentification extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ClientAuthentification() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
 
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter("login");
		String password = request.getParameter("password");

		if(ControllerAuthentification.getInstance().isAuthentified(login, password)){
			Utilisateur user  = DAOUtilisateur.getInstance().getUserByIdentifiant(login);
			request.setAttribute("client", user);
			System.out.println("Authentification Reussie !");
		}
		else
			System.out.println("Echec de l'authentification");
		
		request.getRequestDispatcher("home_client.jsp").forward(request, response);

	}

}
