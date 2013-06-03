package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.TagParameter;

import controller.ControllerAjoutTypeCertification;

/**
 * Servlet implementation class TypesXmlSaving
 */
@WebServlet("/TypesXmlSaving")
public class TypesXmlSaving extends HttpServlet
{

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TypesXmlSaving()
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

		String saveFile = this.getServletContext().getRealPath("/temp_xml/");
		// TODO Auto-generated method stub

		ControllerAjoutTypeCertification controller = ControllerAjoutTypeCertification
				.getInstance();

		String idDefaultRadio = "";

		for (TagParameter type : controller.getParameters().getTypes())
		{
			idDefaultRadio = (String)request.getParameter("optionDefault");
			if (type.getName().equals(idDefaultRadio))
			{
				controller.getParameters().setDefaultType(type);
				break;
			}

		}

		String errMess = "success";

		if (!controller.saveTagXml(saveFile))
		{
			errMess = "error";
		}

		request.getRequestDispatcher("adminHome.jsp?error=" + errMess).forward(
				request, response);

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
