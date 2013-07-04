package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tools.EncoderBase64;
import dao.DAODocumentPDF;
import domain.DocumentPDF;

/**
 * Servlet implementation class SendBase64PDF
 */
public class SendBase64PDF extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendBase64PDF() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * From the id of a document, send the Base64 code of a pdf from a blob
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		long id = Long.parseLong(request.getParameter("id"));
		DocumentPDF doc = DAODocumentPDF.getInstance().getById(id);
		byte[] decode = EncoderBase64.encodingBlobToByteArray(doc.getContenu());
		String chaine = EncoderBase64.byteArraytoStringBase64(decode); 
		response.getWriter().write(chaine);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
