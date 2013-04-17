package servlets;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.Session;

import controller.ControllerCertification;

/**
 * Servlet implementation class CertifierDocument
 */
@WebServlet("/CertifierDocument")
public class CertifierDocument extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CertifierDocument() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String identifiant = request.getParameter("identifiant");
		String url = request.getParameter("url");

		System.out.println("----------Identifiant : "+identifiant);
		System.out.println("----------URL recue : "+url);

		// TODO Auto-generated method stub
		System.out.println("[TEST KEYNECTIS - Servelt] Début du TEST ");
		System.out.println("adresse : "
				+ this.getServletContext().getRealPath("/temp_xml"));

		String basePath = request.getScheme() + "://" + request.getServerName()
				+ ":" + request.getServerPort() + request.getContextPath()
				+ request.getServletPath();

		String saveFile = this.getServletContext().getRealPath("/temp_xml/");
		String certFolder = this.getServletContext().getRealPath("/CERT/");
		System.out.println("Path : " + basePath);

		System.out
		.println("[TEST KEYNECTIS - Servelt] Demande du blob appel a la certification ");

		//		HashMap<String, String> toReturn = ControllerCertification
		//				.getInstance().certificationPDF("MGregoire",
		//						"http://www.marc-gregoire.fr/pdf/cv.pdf", basePath,
		//						saveFile, certFolder);

		HashMap<String, String> toReturn = ControllerCertification
				.getInstance().certificationPDF(identifiant,
						url, basePath,
						saveFile, certFolder);

		System.out.println("[TEST KEYNECTIS - Servelt] Recuperation du blob ");

		request.setAttribute("blob", toReturn.get("blob"));
		request.getSession().setAttribute("transNum", toReturn.get("transNum"));
		request.getSession().setAttribute("CERT", certFolder);
		request.getSession().setAttribute("OUT", saveFile);

		// ajout
		request.getSession().setAttribute("identifiant", "MGregoire");
		request.getSession().setAttribute("urlOriginale",
				"http://www.marc-gregoire.fr/pdf/cv.pdf");
		// fin ajout

		System.out
		.println("[TEST KEYNECTIS - Servelt] Envoi du blob dans l'attribut de la requete + redirection");
		request.getRequestDispatcher("sendToKeynectis.jsp").forward(request,
				response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
