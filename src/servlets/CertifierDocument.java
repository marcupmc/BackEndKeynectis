package servlets;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ControllerCertification;

/**
 * Servlet implementation class CertifierDocument
 */
@WebServlet("/CertifierDocument")
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

		HashMap<String, String> toReturn = ControllerCertification
				.getInstance().certificationPDF("MGregoire",
						"http://www.marc-gregoire.fr/pdf/cv.pdf", basePath,
						saveFile, certFolder);

		System.out.println("[TEST KEYNECTIS - Servelt] Recuperation du blob ");

		request.setAttribute("blob", toReturn.get("blob"));
		request.getSession().setAttribute("transNum", toReturn.get("transNum"));
		request.getSession().setAttribute("CERT", certFolder);
		request.getSession().setAttribute("OUT", saveFile);

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
		// TODO Auto-generated method stub
	}

	// A METTRE DANS LA SERVLET DE RETOUR

	// String transNumInSession = (String)session.getAttribute("transNum");
	// String pdfOutPath =
	// this.getServletContext().getRealPath((String)session.getAttribute("OUT")).concat("/"+transNumInSession+".pdf");
	//
	// log4.info("demo6p4.jsp: Récupération du transNum en session: " +
	// transNumInSession +
	// "; et nom du fichier pdf de sorie à partir de transnum: " +
	// pdfOutPath+"\n");
	//
	// FileOutputStream fos = new FileOutputStream(pdfOutPath);
	// String blob = request.getParameter("blob");
	// ResponseTransId rti = new ResponseTransId();
	// rti.setB64Blob(blob);
	// rti.setCipherCertFilePath(this.getServletContext().getRealPath((String)session.getAttribute("CERT"))+
	// "/demoqs_c.p12", "DemoQS");
	// //rti.setExtractDir("/home/quicksign/simulclient/demoqs/tmp");
	// rti.setOutputStream(fos);
	// String transNum = rti.getTransNum();
	// int status = rti.getStatus();
	// fos.close();
	//
	// log4.info("demo6p4.jsp: Récupération du blob en attribut de requête: "+blob+", et création d'un nouvel rti; attribution du blob au rti, définition du certificat de signature du blob retour demoqs_c.p12, ainsi que de son mdp DemoQS\n");
	// log4.info("demo6p4.jsp: Le blob réponse est donc signé de nouveau. On récupère également le statut de la rti: "
	// +status+
	// ", (1=succès, sinon échec) et on ferme le fichier pdf généré, ouvert pour pouvoir lire dedans et l'affecter au rti.\n");
	//
	// String subDirectory =
	// request.getServletPath().substring(1,request.getServletPath().lastIndexOf("/"));
	// String url =
	// "../index.jsp?pageDemo="+subDirectory+"/demo6p5.jsp&transNum="+transNum+"&status="+status+"&pdfOutPath="+((String)session.getAttribute("OUT")).concat("/"+transNumInSession+".pdf");
	//
	// response.sendRedirect(url);
}
