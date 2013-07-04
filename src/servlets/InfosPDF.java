package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import tools.EncoderBase64;
import tools.ToolsPDF;
import dao.DAODocumentPDF;
import domain.DocumentPDF;

/**
 * Servlet implementation class InfosPDF
 */
public class InfosPDF extends HttpServlet
{

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InfosPDF()
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
		Long id = Long.parseLong(request.getParameter("id"));
		String numPage = request.getParameter("num");

		
		//JSONObject json = ToolsPDF.getInfosPDF(url, Integer.parseInt(numPage));
		
		DocumentPDF documentPDF = DAODocumentPDF.getInstance().getById(id);
		byte[] decode = EncoderBase64.encodingBlobToByteArray(documentPDF.getContenu());
		JSONObject json = ToolsPDF.getInfosPDF(decode, Integer.parseInt(numPage));
		try
		{
			System.out.println("nombre de pages : " + json.get("nbPages"));
		}
		catch (JSONException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.getWriter().write(json.toString());
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

}
