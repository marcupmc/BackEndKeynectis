package servlets;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import tools.EncoderBase64;
import tools.ToolsFTP;
import tools.ToolsPDF;

import com.dictao.keynectis.quicksign.transid.CipherBlobException;
import com.dictao.keynectis.quicksign.transid.DataNotSetException;
import com.dictao.keynectis.quicksign.transid.ParseBlobException;
import com.dictao.keynectis.quicksign.transid.ResponseTransId;
import com.dictao.keynectis.quicksign.transid.SignBlobException;

import dao.DAODocumentPDF;
import domain.AuthorityParameters;
import domain.KeynectisParameters;
import domain.Log;
import domain.TypeLog;

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
		String identifiant = (String) request.getSession().getAttribute("identifiant");
		
		final Marker marker1 = MarkerFactory.getMarker(TypeLog.SIGNATURE_REUSSIE.toString());
		final Marker marker2 = MarkerFactory.getMarker(TypeLog.ERREUR_KEYNECTIS_KWEBSIGN.toString());
		final Logger logger = LoggerFactory.getLogger(EncoderBase64.class);
		
		Log l = new Log();
		l.setIdentifiant_client(identifiant);
		l.setIpadresse(request.getServerName());
		
		String id = (String) request.getSession().getAttribute("id");
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
		String msg="";
		try
		{
			transNum = rti.getTransNum();
			status = rti.getStatus();
			fos.close();
			
			File ftemp= new File(pdfOutPath);
			byte[] bits = ToolsPDF.getBytesFromFile(ftemp);
			
			DAODocumentPDF.getInstance().addContent(Long.parseLong(id), bits);
			ftemp.delete();
			
			 
			if (status==1)
			{	msg="ok";
				logger.info(marker1, "Signature reussie ", l);
			}else{
				msg="error";
				logger.info(marker2, "Erreur de emise par Keynectis ", l);
			}
		}
		catch (DataNotSetException e)
		{
			logger.info(marker2, "Erreur de lecture du blob", l);
			e.printStackTrace();
		}
		catch (ParseBlobException e)
		{
			logger.info(marker2, "Erreur de lecture du blob", l);
			e.printStackTrace();
		}
		catch (SignBlobException e)
		{
			logger.info(marker2, "Erreur de lecture du blob", l);
			e.printStackTrace();
		}
		catch (CipherBlobException e)
		{
			logger.info(marker2, "Erreur de lecture du blob", l);
			e.printStackTrace();
		}

		
//
//		if (null == autho)
//		{
//			ToolsFTP.sendToServer(pdfOutPath, "ftp.marc-gregoire.fr",
//					"www/Keynectis_Certified", "marcgreg", "nCcKMr7E");
//		}
//		else
//		{
//			ToolsFTP.sendToServer(pdfOutPath,
//					((KeynectisParameters) autho).getServPDFCert(),
//					((KeynectisParameters) autho).getPathPDFCert(),
//					((KeynectisParameters) autho).getLoginPDFCert(),
//					((KeynectisParameters) autho).getMdpPDFCert());
//		}

//		File f = new File(pdfOutPath);
//		String filename = f.getName();
//		f.delete();

//		String newPath = "http://www.marc-gregoire.fr/Keynectis_Certified/"
//				+ filename;
//
//		if (null != autho)
//		{
//			newPath = "http://www."
//					+ ((KeynectisParameters) autho).getServPDFCert().substring(
//							((KeynectisParameters) autho).getServPDFCert()
//									.indexOf("."))
//					+ ((KeynectisParameters) autho).getPathPDFCert().substring(
//							((KeynectisParameters) autho).getPathPDFCert()
//									.indexOf("w"))
//					/* "http://www.marc-gregoire.fr/Keynectis_Certified/" */
//					+ filename;
//		}
//
//		System.out.println(newPath);

		deleteTempfiles(temp, name);
		
		

//		String url = "finCertification.jsp?identifiant=" + identifiant + "&id="
//				+ id + "&urlnew=" + newPath;
		String url = "finCertification.jsp?identifiant=" + identifiant+"&id="+id+"&msg="+msg;
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
