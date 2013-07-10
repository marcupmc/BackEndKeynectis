/**
 * 
 */
package controller;

import java.io.File;

import model.AuthorityParameters;
import model.DictaoParamaters;
import model.KeynectisParameters;
import tools.ToolsXML;

/**
 * @author dtadmi
 * 
 */
public class ControllerParameter
{

	// SINGLETON
	public static ControllerParameter getInstance()
	{
		if (null == instance)
		{
			instance = new ControllerParameter();
		}
		return instance;
	}

	private ControllerParameter()
	{
	}
	
	private AuthorityParameters autho = null;
	private String xmlParametersFile = /* "D:\\Users\\dtadmi\\Downloads\\Compressed\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\TestRest\\temp_xml */"\\parameters.xml";
	

	private static ControllerParameter instance;

	/**
	 * Check the parameters of the html KEYNECTIS form and create the
	 * corresponding xml file
	 * 
	 * @param CertPath
	 *            : the directory where are stored the certificates used by the
	 *            certification process
	 * @param TempPath
	 *            : the directory where are saved the temporary files used by
	 *            the process
	 * @param SavePath
	 *            : the directory where are saved the certified files
	 * @param savefile
	 *            : the directory where to save the xml file
	 * @return the AuthorityParameters object created if succeeded, else null
	 */
	public AuthorityParameters validateParameters(String CertPath,
			String TempPath, String SavePath) 
	{
		//AuthorityParameters autho = null;
		if (null == CertPath || null == TempPath || null == SavePath)
		{
			autho = new DictaoParamaters();
		}
		else
		{
			autho = new DictaoParamaters(CertPath, TempPath, SavePath);			
		}
		
		autho = manageXML(autho, SavePath, CertPath);

		return autho;
	}

	/**
	 * Check the parameters of the html KEYNECTIS form and create the
	 * corresponding xml file
	 * 
	 * @param certMetier
	 *            : the certificate file to sign the original file as the
	 *            organism owner
	 * @param mdpMetier
	 *            : the password of the previous certificate
	 * @param idAppMetier
	 *            : the id of the company web application (8 characters max, the
	 *            2 first given by KEYNECTIS)
	 * @param idServMetier
	 *            : the id of the company server (8 characters max)
	 * @param idOrgMetier
	 *            : the id of the customer company (14 characters max)
	 * @param certSign
	 *            : the client certificate file to sign the package before
	 *            sending it the KWS
	 * @param mdpCert
	 *            : the password of the previous certificate
	 * @param certChiff
	 *            : the certificate file to cipher the package to send to KWS
	 * @param certDecipher
	 *            : the certificate file to decipher the package received from
	 *            KWS
	 * @param mdpDecipher
	 *            : the password of the previous certificate
	 * @param servPDFCert
	 *            : the url of the sever where to save the certified documents
	 * @param pathPDFCert
	 *            : the directory of the certified document on the server
	 * @param loginPDFCert
	 *            : the login to access the server
	 * @param mdpPDFCert
	 *            : the password to access the server
	 * @param savefile
	 *            : the local directory where to save the xml file
	 * @return the AuthorityParameters object created if succeeded, else null
	 */
	public AuthorityParameters validateParameters(String CertPath,
			String TempPath, String SavePath, String certMetier,
			String mdpMetier, String idAppMetier, String idServMetier,
			String idOrgMetier, String certSign, String mdpCert,
			String certChiff, String certDecipher, String mdpDecipher,
			String servPDFCert, String pathPDFCert, String loginPDFCert,
			String mdpPDFCert)
	{
		//AuthorityParameters autho = null;
		if (/* null == CertPath || null == TempPath || null == SavePath || */(null == certMetier || ""
				.equals(certMetier))
				|| (null == mdpMetier|| ""
						.equals(mdpMetier))
				|| (null == idAppMetier || ""
						.equals(idAppMetier))
				|| (null == idServMetier || ""
						.equals(idServMetier))
				|| (null == idOrgMetier || ""
						.equals(idOrgMetier))
				|| (null == certSign || ""
						.equals(certSign))
				|| (null == mdpCert || ""
						.equals(mdpCert))
				|| (null == certChiff || ""
						.equals(certChiff))
				|| (null == certDecipher || ""
						.equals(certDecipher))
				|| (null == mdpDecipher || ""
						.equals(mdpDecipher))
				|| (null == servPDFCert || ""
						.equals(servPDFCert))
				|| (null == pathPDFCert || ""
						.equals(pathPDFCert))
				|| (null == loginPDFCert || ""
						.equals(loginPDFCert))
				|| (null == mdpPDFCert || ""
						.equals(mdpPDFCert)))
		{
			autho = null;
		}
		else
		{
			autho = new KeynectisParameters(certMetier, mdpMetier, idAppMetier,
					idServMetier, idOrgMetier, certSign, mdpCert, certChiff,
					certDecipher, mdpDecipher, servPDFCert, pathPDFCert,
					loginPDFCert, mdpPDFCert);
			autho = manageXML(autho, SavePath, CertPath);
		}

		return autho;
	}

	/**
	 * Create the xml file by calling a Tool XML factory
	 * 
	 * @param parameters
	 *            : the AuthorityParameters object containing the form data
	 * @param savePath
	 *            : the directory where to save the xml file
	 * @return the AuthorityParameters object created if succeeded, else null
	 */
	private AuthorityParameters manageXML(AuthorityParameters parameters,
			String savePath, String certFolder)
	{
		if (ToolsXML.createXMLFile(parameters, savePath, certFolder))
			return parameters;
		else
			return null;
	}

	
	/**
	 * @return the autho
	 */
	public AuthorityParameters getAutho()
	{
		return autho;
	}
	
	/**
	 * @return the autho
	 */
	public AuthorityParameters getAutho(String parameterPath, String xmlParametersFile)
	{
		if(null == autho)
		{
			try
			{
				if ((new File(parameterPath + xmlParametersFile)).exists())
				{
					autho = ToolsXML.readConfig(parameterPath + xmlParametersFile);					
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
				System.out.println("ControllerParameter: Exception\n"
						+ "Problème lecture XML: " + e.getMessage());
			}
		}
		return autho;
	}
	
}
