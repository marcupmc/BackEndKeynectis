/**
 * 
 */
package controller;

import tools.ToolsXML;
import domain.AuthorityParameters;
import domain.DictaoParamaters;
import domain.KeynectisParameters;

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
		AuthorityParameters autho = null;
		if (null == CertPath || null == TempPath || null == SavePath)
		{
			autho = null;
		}
		else
		{
			autho = new DictaoParamaters(CertPath, TempPath, SavePath);
			autho = manageXML(autho, SavePath, CertPath);
		}

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
		String errMess="";
		AuthorityParameters autho = null;
		if (/* null == CertPath || null == TempPath || null == SavePath || */null == certMetier
				|| null == mdpMetier
				|| null == idAppMetier
				|| null == idServMetier
				|| null == idOrgMetier
				|| null == certSign
				|| null == mdpCert
				|| null == certChiff
				|| null == certDecipher
				|| null == mdpDecipher
				|| null == servPDFCert
				|| null == pathPDFCert
				|| null == loginPDFCert || null == mdpPDFCert)
		{
			autho = null;
			errMess = "Error";
		}
		else
		{
			autho = new KeynectisParameters(certMetier, mdpMetier, idAppMetier,
					idServMetier, idOrgMetier, certSign, mdpCert, certChiff,
					certDecipher, mdpDecipher, servPDFCert, pathPDFCert,
					loginPDFCert, mdpPDFCert);
			autho = manageXML(autho, SavePath, CertPath);
			errMess = "Success";
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
}
