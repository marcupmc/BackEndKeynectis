package servlets;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.AuthorityParameters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import controller.ControllerCertification;
import dao.DAODocumentPDF;
import domain.DocumentPDF;
import domain.Log;
import domain.TypeLog;

/**
 * Servlet implementation class CertifierDocument
 */
public class CertifierDocument extends HttpServlet
{

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CertifierDocument()
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

		String identifiant = request.getParameter("identifiant");

		final Marker marker = MarkerFactory.getMarker(TypeLog.DEMANDE_SIGNATURE
				.toString());
		final Logger logger = LoggerFactory.getLogger(AddDocument.class);
		Log l = new Log();
		l.setIdentifiant_client(identifiant);
		l.setIpadresse(request.getServerName());
		logger.info(marker, "Demande de signature", l);

		String tempID = request.getParameter("id");
		long id = Long.parseLong(tempID);
		DocumentPDF docToCert = DAODocumentPDF.getInstance().getById(id);
		String url = docToCert.getUrl();

		System.out.println("[TEST KEYNECTIS - Servelt] Début du TEST ");

		String basePath = request.getScheme() + "://" + request.getServerName()
				+ ":" + request.getServerPort() + request.getContextPath()
				+ request.getServletPath();

		String saveFile = this.getServletContext().getRealPath("/temp_xml/");
		String certFolder = this.getServletContext().getRealPath("/CERT/");
		System.out.println("Path : " + basePath);

		System.out
				.println("[TEST KEYNECTIS - Servelt] Demande du blob appel a la certification ");

		AuthorityParameters autho = ControllerCertification.getInstance()
				.getAutho();
		
		HashMap<String, String> toReturn = null;
		
		toReturn = ControllerCertification
				.getInstance().certificationPDF(identifiant, url, basePath,
						saveFile, certFolder, saveFile);
		
		/*if (null == autho)
		{

			toReturn = ControllerCertification
					.getInstance().certificationPDF(identifiant, url, basePath,
							saveFile, certFolder);

		}
		else
		{
			toReturn = ControllerCertification
					.getInstance().certificationPDFFromXml(identifiant, url,
							basePath, saveFile);

		}*/ 
  
		System.out.println("[TEST KEYNECTIS - Servelt] Recuperation du blob ");
		System.out.println("-----TRANSNUM : "+toReturn.get("transNum"));
		System.out.println("-----HASHB64 : "+toReturn.get("hash"));
		request.setAttribute("blob", toReturn.get("blob"));
		request.getSession().setAttribute("transNum", toReturn.get("transNum"));
		request.getSession().setAttribute("CERT", certFolder);
		request.getSession().setAttribute("OUT", saveFile);
		request.getSession().setAttribute("name", docToCert.getName());
		request.getSession().setAttribute("temp", saveFile);

		request.getSession().setAttribute("identifiant", identifiant);
		request.getSession().setAttribute("id", tempID);

		request.getSession().setAttribute("authority", autho);

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
			HttpServletResponse response) throws ServletException, IOException
	{
	}

}
