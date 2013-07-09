package servlets;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.AuthorityParameters;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import controller.ControllerParameter;

/**
 * Servlet implementation class ConfigBackEnd
 */
public class ConfigBackEndConstants extends HttpServlet
{

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ConfigBackEndConstants()
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

		boolean isMultipartContent = ServletFileUpload
				.isMultipartContent(request);
		if (!isMultipartContent)
		{
			System.out.println("You are not trying to upload<br/>");
			return;
		}
		System.out.println("You are trying to upload<br/>");

		String CertPath = "";

		String TempPath = "";

		String SavePath = "";

		String authority = "";
		String certMetier = "";
		String mdpMetier = "";
		String idAppMetier = "";
		String idServMetier = "";
		String idOrgMetier = "";
		String certSign = "";
		String mdpCert = "";
		String certChiff = "";
		String certDecipher = "";
		String mdpDecipher = "";
		String servPDFCert = "";
		String pathPDFCert = "";
		String loginPDFCert = "";
		String mdpPDFCert = "";

		String errMess = "";
		String messType = "";

		File certMetierFile = null;
		File certSignFile = null;
		File certChiffFile = null;
		File certDecipherFile = null;

		AuthorityParameters autho = null;

		ControllerParameter controller = ControllerParameter.getInstance();

		String saveFile = this.getServletContext().getRealPath("/temp_xml/");
		String certFolder = this.getServletContext().getRealPath("/CERT/");
		String tempFolder = this.getServletContext().getRealPath("/temp_xml/");

		System.out.println("Authority = " + authority + "\n saveFile = "
				+ saveFile);

		try
		{
			System.out.println("EmettreServlet");

			// Create a new file upload handler
			DiskFileUpload upload = new DiskFileUpload();

			// Set upload parameters
			int yourMaxMemorySize = 512 * 1024 * 8; // en bytes
			int yourMaxRequestSize = 1024 * 1024 * 8;
			String yourTempDirectory = this.getServletContext().getRealPath(
					"/temp_xml/");// "/home/temp/"; // un répertoire ou tomcat
			// a le droit d'écrire

			upload.setSizeThreshold(yourMaxMemorySize);
			upload.setSizeMax(yourMaxRequestSize);
			upload.setRepositoryPath(yourTempDirectory);

			// Parse the request -on recupère tous les champs du formulaire
			List items = upload.parseRequest(request);

			// Process the uploaded items
			Iterator iter = items.iterator();
			while (iter.hasNext())
			{

				FileItem item = (FileItem) iter.next();

				// Process a regular form field
				if (item.isFormField())
				{
					String fieldname = item.getFieldName();
					String fieldvalue = item.getString();

					{
						if ("authority".equals(fieldname))
							authority = fieldvalue; 

						else if ("mdpMetier".equals(fieldname))
							mdpMetier = fieldvalue;
						else if ("idAppMetier".equals(fieldname))
							idAppMetier = fieldvalue;
						else if ("idServMetier".equals(fieldname))
							idServMetier = fieldvalue;
						else if ("idOrgMetier".equals(fieldname))
							idOrgMetier = fieldvalue;
						else if ("mdpCert".equals(fieldname))
							mdpCert = fieldvalue;
						else if ("mdpDecipher".equals(fieldname))
							mdpDecipher = fieldvalue;
						else if ("servPDFCert".equals(fieldname))
							servPDFCert = fieldvalue;
						else if ("pathPDFCert".equals(fieldname))
							pathPDFCert = fieldvalue;
						else if ("loginPDFCert".equals(fieldname))
							loginPDFCert = fieldvalue;
						else if ("mdpPDFCert".equals(fieldname))
							mdpPDFCert = fieldvalue;
					}

				}
				// Process a file upload
				else
				{
					String fieldname = item.getFieldName();
					String filename = item.getName();
					String contentType = item.getContentType();
					boolean isInMemory = item.isInMemory();
					long sizeInBytes = item.getSize();

					boolean writeToFile = true;
					// Copie directe pour les petits fichiers, sinon streaming
					// (le streaming ne marche pas)
					if (sizeInBytes > 512 * 1024 * 8)
						writeToFile = false;

					// Process a file upload
					if ((writeToFile) /* & (fieldName.equals("source")) */)
					{ // Ecriture directe
						System.out.println("Ecriture directe");

						if ("certMetier".equals(fieldname))
						{

							certMetierFile = new File(yourTempDirectory + "/"
									+ filename);
							item.write(certMetierFile);

							certMetier = certMetierFile.getAbsolutePath();
						}
						else if ("certSign".equals(fieldname))
						{

							certSignFile = new File(yourTempDirectory + "/"
									+ filename);
							item.write(certSignFile);

							certSign = certSignFile.getAbsolutePath();
						}
						else if ("certChiff".equals(fieldname))
						{

							certChiffFile = new File(yourTempDirectory + "/"
									+ filename);
							item.write(certChiffFile);

							certChiff = certChiffFile.getAbsolutePath();
						}
						else if ("certDecipher".equals(fieldname))
						{

							certDecipherFile = new File(yourTempDirectory + "/"
									+ filename);
							item.write(certDecipherFile);

							certDecipher = certDecipherFile.getAbsolutePath();
						}

						/*
						 * File uploadedFile = new File(yourTempDirectory +
						 * filename); item.write(uploadedFile);
						 */
					}
					else
					{ // Streaming
						File uploadedFile = new File(yourTempDirectory + "/"
								+ filename); // ou sinon un nouveau nom de
												// fichier à la place de
												// fileName
						InputStream sourceFile = item.getInputStream();
						OutputStream destinationFile = new FileOutputStream(
								uploadedFile);
						byte buffer[] = new byte[512 * 1024];
						int nbLecture;
						while ((nbLecture = sourceFile.read(buffer)) != -1)
						{
							destinationFile.write(buffer, 0, nbLecture);
						}
						sourceFile.close();
					}

				}
			}

			System.out.println("certMetier: " + certMetier);
			System.out.println("certSign: " + certSign);
			System.out.println("certChiff: " + certChiff);
			System.out.println("certDecipher: " + certDecipher);
		}
		catch (ServletException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		catch (FileUploadException e)
		{
			e.printStackTrace();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		if (("KWS_INTEGRATION_CDS").equals(authority))
		{
			autho = controller.validateParameters(CertPath, TempPath, SavePath,
					certMetier, mdpMetier, idAppMetier, idServMetier,
					idOrgMetier, certSign, mdpCert, certChiff, certDecipher,
					mdpDecipher, servPDFCert, pathPDFCert, loginPDFCert,
					mdpPDFCert);

			if (null == autho)
			{
				errMess = "error";
				messType = "null";
				request.setAttribute("authorityParameter", autho);
				request.getRequestDispatcher(
						"parametrage.jsp?error=" + errMess + "&messType="
								+ messType).forward(request, response);
			}
			else
			{
				/********************* Gestion des erreurs *****************************/

				/*
				 * if (!(new File(CertPath + "\\" + certMetier)).exists()) {
				 * errMess = "error"; messType = "certMetier"; } else if (!(new
				 * File(CertPath + "\\" + certSign)).exists()) { errMess =
				 * "error"; messType = "certSign"; } else if (!(new
				 * File(CertPath + "\\" + certChiff)).exists()) { errMess =
				 * "error"; messType = "certChiff"; } else if (!(new
				 * File(CertPath + "\\" + certDecipher)).exists()) { errMess =
				 * "error"; messType = "certDecipher"; }
				 *//**
				 * 
				 * §§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§
				 * §§§§§§§§§§§§§§§
				 **/
				/*
				 * else
				 */
				{
					errMess = "success";
					messType = "KWS_first";
				}

				request.setAttribute("authorityParameter", autho);
				/*request.getRequestDispatcher(
						"parametrage.jsp?error=" + errMess + "&messType="
								+ messType).forward(request, response);*/
				request.getRequestDispatcher("adminHome.jsp").forward(request,
						response);
			}

		}
		else if (("DICTAO").equals(authority))
		{

			autho = controller.validateParameters(CertPath, TempPath, SavePath);

			request.setAttribute("authorityParameter", autho);
			request.getRequestDispatcher("adminHome.jsp").forward(request,
					response);

		}

	}
}
