package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ControllerParameter;
import domain.AuthorityParameters;

/**
 * Servlet implementation class ConfigBackEnd
 */
@WebServlet("/ConfigBackEnd")
public class ConfigBackEndConstants extends HttpServlet
{

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ConfigBackEndConstants()
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
		// TODO Auto-generated method stub
		String authority = request.getParameter("authority");

		AuthorityParameters autho = null;

		ControllerParameter controller = ControllerParameter.getInstance();

		String saveFile = this.getServletContext().getRealPath("/temp_xml/");
		String certFolder = this.getServletContext().getRealPath("/CERT/");
		String tempFolder = this.getServletContext().getRealPath("/temp_xml/");

		System.out.println("Authority = " + authority + "\n saveFile = "
				+ saveFile);

		String CertPath = request.getParameter("CertPath");
		if ((null == CertPath) || ("".equals(CertPath)))
		{
			CertPath = certFolder; // request.getParameter("CertPath");
		}

		String TempPath = request.getParameter("TempPath");
		if ((null == TempPath) || ("".equals(TempPath)))
		{
			TempPath = tempFolder; // request.getParameter("TempPath");
		}

		String SavePath = request.getParameter("SavePath");
		System.out.println("1:"+SavePath);
		if ((null == SavePath) || ("".equals(SavePath)))
		{
			SavePath = saveFile; // request.getParameter("SavePath");
			System.out.println("2:"+SavePath);
		}

		String errMess = "";
		
		if (("KWS_INTEGRATION_CDS").equals(authority))
		{
			String certMetier = request.getParameter("certMetier");
			String mdpMetier = request.getParameter("mdpMetier");
			String idAppMetier = request.getParameter("idAppMetier");
			String idServMetier = request.getParameter("idServMetier");
			String idOrgMetier = request.getParameter("idOrgMetier");
			String certSign = request.getParameter("certSign");
			String mdpCert = request.getParameter("mdpCert");
			String certChiff = request.getParameter("certChiff");
			String certDecipher = request.getParameter("certDecipher");
			String mdpDecipher = request.getParameter("mdpDecipher");
			String servPDFCert = request.getParameter("servPDFCert");
			String pathPDFCert = request.getParameter("pathPDFCert");
			String loginPDFCert = request.getParameter("loginPDFCert");
			String mdpPDFCert = request.getParameter("mdpPDFCert");

			autho = controller.validateParameters(CertPath, TempPath, SavePath,
					certMetier, mdpMetier, idAppMetier, idServMetier,
					idOrgMetier, certSign, mdpCert, certChiff, certDecipher,
					mdpDecipher, servPDFCert, pathPDFCert, loginPDFCert,
					mdpPDFCert);
			
			if(autho==null)
				errMess = "error";
			else
				errMess = "succes";
			
			request.setAttribute("authorityParameter", autho);
			request.getRequestDispatcher("parametrage.jsp?error="+errMess).forward(request,
					response);

		}
		else if (("DICTAO").equals(authority))
		{

			autho = controller.validateParameters(CertPath, TempPath, SavePath);
			
			request.setAttribute("authorityParameter", autho);
			request.getRequestDispatcher("adminHome.jsp").forward(request,
					response);

		}

		

		// String password = request.getParameter("password");

		/*
		 * if(ControllerAuthentification.getInstance().isAuthentified(login,
		 * password)){ Utilisateur user =
		 * DAOUtilisateur.getInstance().getUserByIdentifiant(login);
		 * request.setAttribute("client", user);
		 * System.out.println("Authentification Reussie !"); } else
		 * System.out.println("Echec de l'authentification");
		 */

		// request.getRequestDispatcher("home_client.jsp").forward(request,
		// response);
	}
}
