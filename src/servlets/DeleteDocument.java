package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DAODocumentPDF;
import dao.DAOLog;
import dao.DAOUtilisateur;
import domain.TypeLog;

/**
 * Servlet implementation class DeleteDocument
 */
@WebServlet("/DeleteDocument")
public class DeleteDocument extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**  
     * @see HttpServlet#HttpServlet()
     */
    public DeleteDocument() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long idClient = Long.parseLong(request.getParameter("idClientDoc"));
		long idDocument = Long.parseLong(request.getParameter("idDocument"));
		
		DAOLog.getInstance().addLog(TypeLog.SUPPRESSION_DOCUMENT, request.getServerName(), DAOUtilisateur.getInstance().getUserById(idClient).getIdentifiant());
		
		DAODocumentPDF.getInstance().deleteDocument(idDocument);
		response.sendRedirect("DetailsClient?id="+idClient);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
