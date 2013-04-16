package servlets;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dictao.keynectis.quicksign.transid.CipherBlobException;
import com.dictao.keynectis.quicksign.transid.DataNotSetException;
import com.dictao.keynectis.quicksign.transid.ParseBlobException;
import com.dictao.keynectis.quicksign.transid.ResponseTransId;
import com.dictao.keynectis.quicksign.transid.SignBlobException;

/**
 * Servlet implementation class ResponseKeynectis
 */
@WebServlet("/ResponseKeynectis")
public class ResponseKeynectis extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ResponseKeynectis()
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

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		System.out.println("URL DE RETOUR : " + request.getRequestURL() + "||"
				+ request.getRequestedSessionId());

		String blob = (String) request.getParameter("blob");

		System.out.println("toto2 : "
				+ request.getSession().getAttribute("toto"));
		String adresseCertificat = (String) request.getSession().getAttribute(
				"CERT")
				+ "/demoqs_c.p12";
		String transNumInSession = (String) request.getSession().getAttribute(
				"transNum");
		String pdfOutPath = (String) request.getSession().getAttribute("OUT")
				+ "\\" + transNumInSession + ".pdf";

		System.out.println("Adresse Certificat : " + adresseCertificat);
		System.out.println("Trans num session : " + transNumInSession);
		System.out.println("pdfOutPath : " + pdfOutPath);

		try
		{
			FileOutputStream fos = new FileOutputStream(pdfOutPath);
			
			System.out.println("pdfOutPath arranged: "+pdfOutPath.replaceAll("\\\\", "/"));
			
			ResponseTransId rti = new ResponseTransId();
			rti.setB64Blob(blob);

			rti.setCipherCertFilePath(adresseCertificat, "DemoQS");
			rti.setOutputStream(fos);
			String transNum = "";
			int status = -1;
		
			transNum = rti.getTransNum();
			status = rti.getStatus();
			
			fos.close(); 

			// String subDirectory =
			// request.getServletPath().substring(1,request.getServletPath().lastIndexOf("/"));
			String url = "index.jsp?pageDemo=demoPDFSMS/demo6p5.jsp&transNum="
					+ transNum + "&status=" + status + "&pdfOutPath=" + pdfOutPath.replaceAll("\\\\", "/");

			response.sendRedirect(url);
		}
		catch (DataNotSetException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (ParseBlobException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (SignBlobException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (CipherBlobException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (FileNotFoundException e) 
		{
			// TODO: handle exception
			e.printStackTrace();
		}

		
	}

}
