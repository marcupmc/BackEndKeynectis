package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.AuthorityParameters;
import model.TagParameters;
import controller.ControllerAjoutTypeCertification;
import controller.ControllerParameter;

/**
 * Servlet implementation class TypeCertifConfig
 */

public class TypeCertifConfig extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TypeCertifConfig() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		TagParameters types = ControllerAjoutTypeCertification.getInstance().getParametersFromDAO();
		request.setAttribute("types",types);
		AuthorityParameters autho = ControllerParameter.getInstance().getAutho();
		request.setAttribute("authorityParameter", autho); 
		request.getRequestDispatcher("typeCertifConfig.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
