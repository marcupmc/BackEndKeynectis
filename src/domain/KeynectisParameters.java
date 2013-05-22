/**
 * 
 */
package domain;

/**
 * @author dtadmi
 * 
 */
public class KeynectisParameters extends AuthorityParameters
{

	/**
	 * @return the authority
	 */
	public String getAuthority()
	{
		return authority;
	}

	private String certMetier = "CERT/demoqs_s.p12";
	private String mdpMetier = "DEMOQS";
	private String idAppMetier = "ZZDEMAV1";
	private String idServMetier = "DEMO";
	private String idOrgMetier = "PDFSMS";
	private String certSign = "demoqs_i.p12";
	private String mdpCert = "DEMOQS";
	private String certChiff = "certQSkeyncryp.cer";
	private String certDecipher = "demoqs_c.p12";
	private String mdpDecipher = "DEMOQS";
	private String servPDFCert = "ftp.marc-gregoire.fr";
	private String pathPDFCert = "www/Keynectis_Certified";
	private String loginPDFCert = "marcgreg";
	private String mdpPDFCert = "nCcKMr7E";

	/**
	 * 
	 */
	public KeynectisParameters()
	{
		super();
		// TODO Auto-generated constructor stub
		type = 1;
		authority = "KWS_INTEGRATION_CDS";
	}

	/**
	 * Create the parameters objet for the KWS certificate authority
	 * 
	 * @param certMetier
	 *            : the certificate file to sign the original file as the
	 *            organism owner
	 * @param mdpMetier
	 *            : the password of the certificate file to sign the original
	 *            file as the organism owner
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
	 *            : the password of the client certificate file to sign the
	 *            package before sending it the KWS
	 * @param certChiff
	 *            : the certificate file to cipher the package to send to KWS
	 * @param certDecipher
	 *            : the certificate file to decipher the package received from
	 *            KWS
	 * @param mdpDecipher
	 *            : the password of the certificate file to decipher the package
	 *            received from KWS
	 * @param pathPDFCert
	 *            : Le répertoire local de stockage des fichier certifiés
	 */
	public KeynectisParameters(String certMetier, String mdpMetier,
			String idAppMetier, String idServMetier, String idOrgMetier,
			String certSign, String mdpCert, String certChiff,
			String certDecipher, String mdpDecipher, String pathPDFCert)
	{
		super();
		this.certMetier = certMetier;
		this.mdpMetier = mdpMetier;
		this.idAppMetier = idAppMetier;
		this.idServMetier = idServMetier;
		this.idOrgMetier = idOrgMetier;
		this.certSign = certSign;
		this.mdpCert = mdpCert;
		this.certChiff = certChiff;
		this.certDecipher = certDecipher;
		this.mdpDecipher = mdpDecipher;
		this.pathPDFCert = pathPDFCert;
		type = 1;
		authority = "KWS_INTEGRATION_CDS";
	}

	/**
	 * Create the parameters objet for the KWS certificate authority
	 * 
	 * @param certMetier
	 *            : the certificate file to sign the original file as the
	 *            organism owner
	 * @param mdpMetier
	 *            : the password of the certificate file to sign the original
	 *            file as the organism owner
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
	 *            : the password of the client certificate file to sign the
	 *            package before sending it the KWS
	 * @param certChiff
	 *            : the certificate file to cipher the package to send to KWS
	 * @param certDecipher
	 *            : the certificate file to decipher the package received from
	 *            KWS
	 * @param mdpDecipher
	 *            : the password of the certificate file to decipher the package
	 *            received from KWS
	 * @param servPDFCert
	 *            : the url of the sever where to save the certified documents
	 * @param pathPDFCert
	 *            : the directory of the certified document on the server
	 * @param loginPDFCert
	 *            : the login to access the server
	 * @param mdpPDFCert
	 *            : the password to access the server
	 */
	public KeynectisParameters(String certMetier, String mdpMetier,
			String idAppMetier, String idServMetier, String idOrgMetier,
			String certSign, String mdpCert, String certChiff,
			String certDecipher, String mdpDecipher, String servPDFCert,
			String pathPDFCert, String loginPDFCert, String mdpPDFCert)
	{
		super();
		this.certMetier = certMetier;
		this.mdpMetier = mdpMetier;
		this.idAppMetier = idAppMetier;
		this.idServMetier = idServMetier;
		this.idOrgMetier = idOrgMetier;
		this.certSign = certSign;
		this.mdpCert = mdpCert;
		this.certChiff = certChiff;
		this.certDecipher = certDecipher;
		this.mdpDecipher = mdpDecipher;
		this.servPDFCert = servPDFCert;
		this.pathPDFCert = pathPDFCert;
		this.loginPDFCert = loginPDFCert;
		this.mdpPDFCert = mdpPDFCert;
		type = 1;
		authority = "KWS_INTEGRATION_CDS";
	}

	/**
	 * @return the servPDFCert : the url of the sever where to save the
	 *         certified documents
	 */
	public String getServPDFCert()
	{
		return servPDFCert;
	}

	/**
	 * @param servPDFCert
	 *            the servPDFCert to set
	 */
	public void setServPDFCert(String servPDFCert)
	{
		this.servPDFCert = servPDFCert;
	}

	/**
	 * @return the loginPDFCert : the login to access the server
	 */
	public String getLoginPDFCert()
	{
		return loginPDFCert;
	}

	/**
	 * @param loginPDFCert
	 *            the loginPDFCert to set
	 */
	public void setLoginPDFCert(String loginPDFCert)
	{
		this.loginPDFCert = loginPDFCert;
	}

	/**
	 * @return the mdpPDFCert : the password to access the server
	 */
	public String getMdpPDFCert()
	{
		return mdpPDFCert;
	}

	/**
	 * @param mdpPDFCert
	 *            the mdpPDFCert to set
	 */
	public void setMdpPDFCert(String mdpPDFCert)
	{
		this.mdpPDFCert = mdpPDFCert;
	}

	/**
	 * @return the certMetier : the certificate file to sign the original file
	 *         as the organism owner
	 */
	public String getCertMetier()
	{
		return certMetier;
	}

	/**
	 * @param certMetier
	 *            : the certMetier to set
	 */
	public void setCertMetier(String certMetier)
	{
		this.certMetier = certMetier;
	}

	/**
	 * @return the mdpMetier : the password of the previous certificate
	 */
	public String getMdpMetier()
	{
		return mdpMetier;
	}

	/**
	 * @param mdpMetier
	 *            : the mdpMetier to set
	 */
	public void setMdpMetier(String mdpMetier)
	{
		this.mdpMetier = mdpMetier;
	}

	/**
	 * @return the idAppMetier : the id of the company web application (8
	 *         characters max, the 2 first given by KEYNECTIS)
	 */
	public String getIdAppMetier()
	{
		return idAppMetier;
	}

	/**
	 * @param idAppMetier
	 *            : the idAppMetier to set
	 */
	public void setIdAppMetier(String idAppMetier)
	{
		this.idAppMetier = idAppMetier;
	}

	/**
	 * @return the idServMetier : the id of the company server (8 characters
	 *         max)
	 */
	public String getIdServMetier()
	{
		return idServMetier;
	}

	/**
	 * @param idServMetier
	 *            : the idServMetier to set
	 */
	public void setIdServMetier(String idServMetier)
	{
		this.idServMetier = idServMetier;
	}

	/**
	 * @return the idOrgMetier : the id of the customer company (14 characters
	 *         max)
	 */
	public String getIdOrgMetier()
	{
		return idOrgMetier;
	}

	/**
	 * @param idOrgMetier
	 *            : the idOrgMetier to set
	 */
	public void setIdOrgMetier(String idOrgMetier)
	{
		this.idOrgMetier = idOrgMetier;
	}

	/**
	 * @return the certSign : the client certificate file to sign the package
	 *         before sending it the KWS
	 */
	public String getCertSign()
	{
		return certSign;
	}

	/**
	 * @param certSign
	 *            : the certSign to set
	 */
	public void setCertSign(String certSign)
	{
		this.certSign = certSign;
	}

	/**
	 * @return the mdpCert : the password of the client certificate file to sign
	 *         the package before sending it the KWS
	 */
	public String getMdpCert()
	{
		return mdpCert;
	}

	/**
	 * @param mdpCert
	 *            : the mdpCert to set
	 */
	public void setMdpCert(String mdpCert)
	{
		this.mdpCert = mdpCert;
	}

	/**
	 * @return the certChiff : the certificate file to cipher the package to
	 *         send to KWS
	 */
	public String getCertChiff()
	{
		return certChiff;
	}

	/**
	 * @param certChiff
	 *            : the certChiff to set
	 */
	public void setCertChiff(String certChiff)
	{
		this.certChiff = certChiff;
	}

	/**
	 * @return the certDecipher : the certificate file to decipher the package
	 *         received from KWS
	 */
	public String getCertDecipher()
	{
		return certDecipher;
	}

	/**
	 * @param certDecipher
	 *            : the certDecipher to set
	 */
	public void setCertDecipher(String certDecipher)
	{
		this.certDecipher = certDecipher;
	}

	/**
	 * @return the mdpDecipher : the password of the certificate file to
	 *         decipher the package received from KWS
	 */
	public String getMdpDecipher()
	{
		return mdpDecipher;
	}

	/**
	 * @param mdpDecipher
	 *            : the mdpDecipher to set
	 */
	public void setMdpDecipher(String mdpDecipher)
	{
		this.mdpDecipher = mdpDecipher;
	}

	/**
	 * @return the pathPDFCert : the directory of the certified document on the
	 *         server
	 */
	public String getPathPDFCert()
	{
		return pathPDFCert;
	}

	/**
	 * @param pathPDFCert
	 *            : the pathPDFCert to set
	 */
	public void setPathPDFCert(String pathPDFCert)
	{
		this.pathPDFCert = pathPDFCert;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see domain.AuthorityParameters#getType()
	 */
	@Override
	public int getType()
	{
		// TODO Auto-generated method stub
		return type;
	}
}
