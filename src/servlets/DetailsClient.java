package servlets;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ControllerAjoutTypeCertification;
import dao.DAOUtilisateur;
import domain.DocumentPDF;
import domain.Utilisateur;

/**
 * Servlet implementation class DetailsClient
 */
public class DetailsClient extends HttpServlet
{

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DetailsClient()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		String msg = (String) request.getParameter("msg");
		if (msg != null)
			request.setAttribute("msg", msg);
		long idClient = Long.parseLong(request.getParameter("id"));
		Utilisateur user = DAOUtilisateur.getInstance().getUserById(idClient);
		request.setAttribute("client", user);
		request.getRequestDispatcher("detailsClient.jsp").forward(request,
				response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		
	}

}
