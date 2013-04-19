package servlets;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tools.ToolsFTP;
import tools.ToolsPDF;

import com.dictao.keynectis.quicksign.transid.CipherBlobException;
import com.dictao.keynectis.quicksign.transid.DataNotSetException;
import com.dictao.keynectis.quicksign.transid.ParseBlobException;
import com.dictao.keynectis.quicksign.transid.ResponseTransId;
import com.dictao.keynectis.quicksign.transid.SignBlobException;
import com.itextpdf.text.log.SysoLogger;

import dao.DAOUtilisateur;

/**
 * Servlet implementation class ResponseKeynectis
 */
@WebServlet("/ResponseKeynectis")
public class ResponseKeynectis extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ResponseKeynectis() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println("URL DE RETOUR : "+request.getRequestURL()+ "||"+request.getRequestedSessionId() );

		String blob = (String)request.getParameter("blob");

		System.out.println("toto2 : "+request.getSession().getAttribute("toto")); 
		String adresseCertificat = (String)request.getSession().getAttribute("CERT")+"/demoqs_c.p12";
		String transNumInSession = (String)request.getSession().getAttribute("transNum");
		String pdfOutPath = (String)request.getSession().getAttribute("OUT")+"\\"+transNumInSession+".pdf";
		String temp = (String)request.getSession().getAttribute("temp");
		String name = (String)request.getSession().getAttribute("name");

		System.out.println("Adresse Certificat : "+adresseCertificat);
		System.out.println("Trans num session : "+transNumInSession);
		System.out.println("pdfOutPath : "+pdfOutPath);

		FileOutputStream fos = new FileOutputStream(pdfOutPath);
		ResponseTransId rti = new ResponseTransId();
		rti.setB64Blob(blob);

		rti.setCipherCertFilePath(adresseCertificat, "DemoQS"); 
		rti.setOutputStream(fos);
		String transNum="";
		int status=-1; 
		try {
			transNum = rti.getTransNum();
			status = rti.getStatus();
		} catch (DataNotSetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseBlobException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SignBlobException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CipherBlobException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		fos.close();
		
		//Send to Serveur
		
		ToolsFTP.sendToServer(pdfOutPath, "ftp.marc-gregoire.fr", "www/Keynectis_Certified", "marcgreg", "nCcKMr7E");
		
		//A Changer
		
		File f = new File(pdfOutPath);
		String filename  = f.getName();
		f.delete();
		
		String newPath = "http://www.marc-gregoire.fr/Keynectis_Certified/"+filename;
		
		
		
		deleteTempfiles(temp, name);
		//AJOUT
		String identifiant=(String)request.getSession().getAttribute("identifiant");
		String id = (String)request.getSession().getAttribute("id");
		//DAOUtilisateur.getInstance().certifiedDocument(identifiant,urlOriginale);
		
		//FIN AJOUT
		
		
	//	String subDirectory = request.getServletPath().substring(1,request.getServletPath().lastIndexOf("/"));
		//String url = "index.jsp?pageDemo=demoPDFSMS/demo6p5.jsp&transNum="+transNum+"&status="+status+"&pdfOutPath="+pdfOutPath.replaceAll("\\\\", "/");
		String url  = "finCertification.jsp?identifiant="+identifiant+"&id="+id+"&urlnew="+newPath;
		response.sendRedirect(url);
	}
	
	private void deleteTempfiles(String url, String name){
		File f1 = new File(url+"\\"+name+".pdf");
		f1.delete();
		File f2 = new File(url+"\\"+name+".xml");
		f2.delete();
		File f3  = new File(url+"\\"+name+".xml.dsig");
		f3.delete();
		
	}

}
