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
 * Servlet implementation class AddType
 */
@WebServlet("/AddType")
public class AddType extends HttpServlet
{

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddType()
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

		String name = request.getParameter("name");
		String id = request.getParameter("id");
		// String reason = request.getParameter("reason");
		// String location = request.getParameter("location");
		// String contact = request.getParameter("contact");

		ControllerAjoutTypeCertification controller = ControllerAjoutTypeCertification
				.getInstance();

		request.setAttribute("type", controller.getType(id, name));

		String action = request.getParameter("action");

		if ("modify".equals(action))
		{
			request.getRequestDispatcher("addType.jsp?option=modify"/*
																	 * ?id=
																	 * " + id + "
																	 * &name=
																	 * " + name + "
																	 * &reason="
																	 * + reason
																	 * +
																	 * "&location="
																	 * +
																	 * location
																	 * +
																	 * "&contact="
																	 * + contact
																	 */)
					.forward(request, response);
		}
		/*
		 * else if("edit".equals(action)) {
		 * 
		 * }
		 */
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub

		String name = request.getParameter("typeName");
		String id = request.getParameter("typeIdentifiant");
		String reason = request.getParameter("reason");
		String location = request.getParameter("location");
		String contact = request.getParameter("contact");

		String errMess = "success";
		String messType = "KWS";

		String url = "parametrage.jsp?error=" + errMess + "&messType="
				+ messType;

		TagParameter type = new TagParameter(name, id, reason, location,
				contact);
		ControllerAjoutTypeCertification controller = ControllerAjoutTypeCertification
				.getInstance();

		String action = request.getParameter("action");

		if ("save".equals(action))
		{
			if (!controller.addType(type))
			{
				errMess = "adding_type_error";
				url = "addType.jsp?error=" + errMess;
			}
		}

		/*
		 * if ("modify".equals(action)) { request.getRequestDispatcher(
		 * "addType.jsp?option=modify"?id=" + id + "&name=" + name + "&reason="
		 * + reason + "&location=" + location + "&contact=" +
		 * contact).forward(request, response); }
		 */
		else if ("edit".equals(action))
		{
			String editName = request.getParameter("name");
			String editId = request.getParameter("id");

			controller.getType(editId, editName).modifyParameters(name, id,
					reason, location, contact);
		}

		request.setAttribute("types", controller.getParameters());

		request.getRequestDispatcher(url).forward(request, response);
	}

}
