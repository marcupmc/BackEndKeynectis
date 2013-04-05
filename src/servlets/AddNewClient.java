package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ControllerAjoutClient;

/**
 * Servlet implementation class AddNewClient
 */
@WebServlet("/AddNewClient")
public class AddNewClient extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddNewClient() {
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
		// TODO Auto-generated method stub
		//Ici il faut checker les entrées 
		String firstname =  request.getParameter("inputFirstName");
		String lastname = request.getParameter("inputName");
		String email = request.getParameter("inputEmail");
		String telephone = request.getParameter("inputPhone");
		String password = request.getParameter("inputPassword");
		String confirm = request.getParameter("inputConfirm");
		String identifiant = request.getParameter("inputIdentifiant");

		String messageErreur = "";

		if(firstname==null || firstname.length()== 0 ||
				lastname==null || lastname.length()==0 ||
				email==null || email.length()==0 ||
				telephone == null || telephone.length()==0 ||
				password == null || password.length()==0 ||
				confirm == null || confirm.length()==0 ||
				identifiant == null || identifiant.length()==0 
				) 
			// A remplacer par une redirection vers une page d'erreur
		{
			messageErreur="empty_field";
		}
		else
		{
			if(!password.equals(confirm)) messageErreur="missmatch_pwd";//Renvoyer vers une page d'erreur
			//et ajouter le nouveau Client dans la base
			// EN faisant appel a un controlleur qui va verifier la coherence des entrees
			else if(ControllerAjoutClient.getInstance().addClientInDB(identifiant, lastname, firstname, email, telephone, password))
				messageErreur="success";
			else
				//envoyer vers une page d erreur
				messageErreur="error_add";
		}
		
		request.getRequestDispatcher("addClient.jsp?msg="+messageErreur+"").forward(request, response);
	}

}
