package servlets;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tools.ToolsFTP;

import com.dictao.keynectis.quicksign.transid.CipherBlobException;
import com.dictao.keynectis.quicksign.transid.DataNotSetException;
import com.dictao.keynectis.quicksign.transid.ParseBlobException;
import com.dictao.keynectis.quicksign.transid.ResponseTransId;
import com.dictao.keynectis.quicksign.transid.SignBlobException;

import domain.AuthorityParameters;
import domain.KeynectisParameters;

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

		AuthorityParameters autho = (AuthorityParameters) request.getSession()
				.getAttribute("authority");

		String blob = (String) request.getParameter("blob");
		String adresseCertificat = (String) request.getSession().getAttribute(
				"CERT")
				+ "/demoqs_c.p12";
		String transNumInSession = (String) request.getSession().getAttribute(
				"transNum");
		String pdfOutPath = (String) request.getSession().getAttribute("OUT")
				+ "\\" + transNumInSession + ".pdf";
		String temp = (String) request.getSession().getAttribute("temp");
		String name = (String) request.getSession().getAttribute("name");

		System.out.println("Adresse Certificat : " + adresseCertificat);
		System.out.println("Trans num session : " + transNumInSession);
		System.out.println("pdfOutPath : " + pdfOutPath);

		FileOutputStream fos = new FileOutputStream(pdfOutPath);
		ResponseTransId rti = new ResponseTransId();
		rti.setB64Blob(blob);

		rti.setCipherCertFilePath(adresseCertificat, "DemoQS");
		rti.setOutputStream(fos);
		String transNum = "";
		int status = -1;
		try
		{
			transNum = rti.getTransNum();
			status = rti.getStatus();
		}
		catch (DataNotSetException e)
		{
			e.printStackTrace();
		}
		catch (ParseBlobException e)
		{
			e.printStackTrace();
		}
		catch (SignBlobException e)
		{
			e.printStackTrace();
		}
		catch (CipherBlobException e)
		{
			e.printStackTrace();
		}

		fos.close();

		/*
		 * ToolsFTP.sendToServer(pdfOutPath, "ftp.marc-gregoire.fr",
		 * "www/Keynectis_Certified", "marcgreg", "nCcKMr7E");
		 */
		ToolsFTP.sendToServer(pdfOutPath,
				((KeynectisParameters) autho).getServPDFCert(),
				((KeynectisParameters) autho).getPathPDFCert(),
				((KeynectisParameters) autho).getLoginPDFCert(),
				((KeynectisParameters) autho).getMdpPDFCert());
		File f = new File(pdfOutPath);
		String filename = f.getName();
		f.delete();

		/*
		 * String newPath = "http://www.marc-gregoire.fr/Keynectis_Certified/" +
		 * filename;
		 */
		String newPath = "http://www."
				+ ((KeynectisParameters) autho).getServPDFCert().substring(
						((KeynectisParameters) autho).getServPDFCert().indexOf(
								"."))
				+ ((KeynectisParameters) autho).getPathPDFCert().substring(
						((KeynectisParameters) autho).getPathPDFCert().indexOf(
								"w"))
				/* "http://www.marc-gregoire.fr/Keynectis_Certified/" */
				+filename;
		
		System.out.println(newPath);

		deleteTempfiles(temp, name);
		String identifiant = (String) request.getSession().getAttribute(
				"identifiant");
		String id = (String) request.getSession().getAttribute("id");

		String url = "finCertification.jsp?identifiant=" + identifiant + "&id="
				+ id + "&urlnew=" + newPath;
		response.sendRedirect(url);
	}

	/**
	 * Delete temporary files creates during the process of certification
	 * 
	 * @param url
	 * @param name
	 */
	private void deleteTempfiles(String url, String name)
	{
		File f1 = new File(url + "\\" + name + ".pdf");
		f1.delete();
		File f2 = new File(url + "\\" + name + ".xml");
		f2.delete();
		File f3 = new File(url + "\\" + name + ".xml.dsig");
		f3.delete();

	}

}
