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
public class AddSignaturePDF extends HttpServlet
{

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddSignaturePDF()
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

		long idClient = Long.parseLong(request.getParameter("idOwner")
				.toString());
		long id = Long.parseLong(request.getParameter("id").toString());

		float pdfX = Float.parseFloat(request.getParameter("valPDFX")
				.toString());
		float pdfY = Float.parseFloat(request.getParameter("valPDFY")
				.toString());

		float sigX = Float.parseFloat(request.getParameter("valX").toString());
		float sigY = Float.parseFloat(request.getParameter("valY").toString());

		float height = Float.parseFloat(request.getParameter("height")
				.toString());
		float width = Float
				.parseFloat(request.getParameter("width").toString());

		float realX = sigX - pdfX;
		float realY = sigY - pdfY;

		DAODocumentPDF.getInstance().setPosSignature(id, realX, realY, width,
				height, 1, "signTEST");

		response.sendRedirect("DetailsClient?id=" + idClient);

	}

}
