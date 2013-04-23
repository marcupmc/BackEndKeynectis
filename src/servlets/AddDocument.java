package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tools.ToolsPDF;

import dao.DAODocumentPDF;

/**
 * Servlet implementation class AddDocument
 */
@WebServlet("/AddDocument")
public class AddDocument extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */ 
	public AddDocument() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub

		//Reucuperer l'id qui est en champs caché du formulaire
		long idClient =Long.parseLong(request.getParameter("idClient"));
		String nameDocument = request.getParameter("name0"); //A changer si on veut ajouter plusieurs fichiers en meme temps
		String urlDocument = request.getParameter("url0");
		String containsSign = request.getParameter("containsSign");

		String msgErr = "";
		//Verifier les entrees
		boolean ok = false;
		if(urlDocument==null||urlDocument.length()==0||nameDocument==null||nameDocument.length()==0)
			msgErr= "bad_parameters";
		else{
			// Ajouter le document en appelant le DAO
			if(containsSign.equals("non")){
				if(DAODocumentPDF.getInstance().addDocument(idClient, nameDocument, urlDocument))
					msgErr = "ok";
			}else{
				//Checker le nom de la signature
				String sigName  = request.getParameter("signame");
				if(sigName==null||sigName.length()==0)msgErr="bad_signame";
				else {
					if(ToolsPDF.checkSignature(urlDocument, sigName)&&DAODocumentPDF.getInstance().addDocument(idClient, nameDocument, urlDocument,sigName))msgErr = "ok";
					else msgErr="signame_err";
				}
				//On verifie son existence -> signame_Err
			}
		}
//		if(!ok)
			
		//request.getRequestDispatcher("detailsClient.jsp").forward(request, response);
		response.sendRedirect("DetailsClient?id="+idClient+"&msg="+msgErr);
	}

}
