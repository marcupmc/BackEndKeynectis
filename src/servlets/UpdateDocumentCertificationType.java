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
 * Servlet implementation class UpdateDocumentCertificationType
 */
public class UpdateDocumentCertificationType extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateDocumentCertificationType() {
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
		long idClient = Long.parseLong(request.getParameter("idClient"));
		String select = "";
		Utilisateur user = DAOUtilisateur.getInstance().getUserById(idClient);
		Set<DocumentPDF> docs = user.getDocuments();

		String msg = "certif_adding_success";

		for (DocumentPDF doc : docs)
		{
			select = (String) request.getParameter(doc.getName()); // selected
																	// certification
																	// option in
																	// the
																	// select
																	// tag
			if (!("Choisir un type".equals(select)))
				if (!ControllerAjoutTypeCertification.getInstance()
						.addTypeToDocument(
								doc.getId(),
								ControllerAjoutTypeCertification.getInstance()
										.getType(select)))
				{
					msg = "cetif_adding_error";
					request.setAttribute("typ", select);
					request.setAttribute("docu", doc.getName());
				}

		}

		
		response.sendRedirect("DetailsClient?id=" + idClient + "&msg=" + msg);
	}

}
