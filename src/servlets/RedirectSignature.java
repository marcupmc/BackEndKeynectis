package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DAODocumentPDF;
import domain.DocumentPDF;

/**
 * Servlet implementation class RedirectSignature
 */
@WebServlet("/RedirectSignature")
public class RedirectSignature extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RedirectSignature() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long id  = Long.parseLong(request.getParameter("id"));
		DocumentPDF doc = DAODocumentPDF.getInstance().getById(id);
		request.setAttribute("doc", doc);
		//request.getRequestDispatcher("signatureEditor.jsp").forward(request, response);
		request.getRequestDispatcher("pdfReader.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
