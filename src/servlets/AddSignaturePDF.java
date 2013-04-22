package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DAODocumentPDF;

/**
 * Servlet implementation class AddSignaturePDF
 */
@WebServlet("/AddSignaturePDF")
public class AddSignaturePDF extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddSignaturePDF() {
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
		
		long id = Long.parseLong(request.getParameter("id").toString());
		int pdfX = Integer.parseInt(request.getParameter("valPDFX").toString());
		int pdfY = Integer.parseInt(request.getParameter("valPDFY").toString());
		
		int sigX = Integer.parseInt(request.getParameter("valX").toString());
		int sigY = Integer.parseInt(request.getParameter("valY").toString());
		
		int height = Integer.parseInt(request.getParameter("height").toString());
		int width = Integer.parseInt(request.getParameter("width").toString());
		
		int realX  =sigX-pdfX;
		int realY = sigY-pdfY;
		
		DAODocumentPDF.getInstance().setPosSignature(id, realX, realY,width,height);
		response.sendRedirect("DetailsClient?id="+id);
		
		
		
	}

}
