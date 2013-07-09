/**
 * 
 */
package model;

/**
 * @author dtadmi
 * 
 */
public class AuthorityParameters
{

	protected String authority = "KWS_INTEGRATION_CDS";
	protected int type = 0;

	
	/**
	 * @return the name of the authority
	 */
	public String getAuthority()
	{
		return authority;
	}

	protected String CertPath = "";
	protected String TempPath = "";
	protected String SavePath = "";

	/**
	 * @return the type
	 */
	public int getType()
	{
		if (("KEYNECTIS KWS").equals(authority))
			type = 1;
		else if (("DICTAO").equals(authority))
			type = 2;

		return type;
	}

	/**
	 * 
	 */
	public AuthorityParameters()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the certPath
	 */
	public String getCertPath()
	{
		return CertPath;
	}

	/**
	 * @param certPath
	 *            the certPath to set
	 */
	public void setCertPath(String certPath)
	{
		CertPath = certPath;
	}

	/**
	 * @return the tempPath
	 */
	public String getTempPath()
	{
		return TempPath;
	}

	/**
	 * @param tempPath
	 *            the tempPath to set
	 */
	public void setTempPath(String tempPath)
	{
		TempPath = tempPath;
	}

	/**
	 * @return the savePath
	 */
	public String getSavePath()
	{
		return SavePath;
	}

	/**
	 * @param savePath
	 *            the savePath to set
	 */
	public void setSavePath(String savePath)
	{
		SavePath = savePath;
	}

}
