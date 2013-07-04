package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import controller.ControllerAjoutClient;
import domain.Log;
import domain.TypeLog;

/**
 * Servlet implementation class AddNewClient
 */
public class AddNewClient extends HttpServlet
{

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddNewClient()
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
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		String firstname = request.getParameter("inputFirstName");
		String lastname = request.getParameter("inputName");
		String email = request.getParameter("inputEmail");
		String telephone = request.getParameter("inputPhone");
		String password = request.getParameter("inputPassword");
		String confirm = request.getParameter("inputConfirm");
		String identifiant = request.getParameter("inputIdentifiant");
		String messageErreur = "";

		if (firstname == null || firstname.length() == 0 || lastname == null
				|| lastname.length() == 0 || email == null
				|| email.length() == 0 || telephone == null
				|| telephone.length() == 0 || password == null
				|| password.length() == 0 || confirm == null
				|| confirm.length() == 0 || identifiant == null
				|| identifiant.length() == 0)
		{
			messageErreur = "empty_field";
		}
		else
		{
			if (!password.equals(confirm))
				messageErreur = "missmatch_pwd";
			else if (ControllerAjoutClient.getInstance().addClientInDB(
					identifiant, lastname, firstname, email, telephone,
					password))
				messageErreur = "success";
			else
				messageErreur = "error_add";
		}

		String urlRetour = "";
		if (messageErreur.equals("success"))
		{
			final Marker marker = MarkerFactory.getMarker(TypeLog.AJOUT_CLIENT
					.toString());
			final Logger logger = LoggerFactory.getLogger(AddNewClient.class);
			Log l = new Log();
			l.setIdentifiant_client(identifiant);
			l.setIpadresse(request.getServerName());
			logger.info(marker, "Ajout nouveau client", l);

			urlRetour = "FindClient?recherche=";

		}
		else
		{
			urlRetour = "addClient.jsp?error=" + messageErreur;
		}
		response.sendRedirect(urlRetour);
	}

}
