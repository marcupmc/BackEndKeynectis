/**
 * 
 */
package domain;

/**
 * @author dtadmi
 * 
 */
public class DictaoParamaters extends AuthorityParameters
{

	/**
	 * @return the authority
	 */
	public String getAuthority()
	{
		return authority;
	}

	private String CertPath = "";
	private String TempPath = "";
	private String SavePath = "";

	/**
	 * 
	 */
	public DictaoParamaters()
	{
		super();
		// TODO Auto-generated constructor stub
		authority = "DICTAO";
		type = 2;
	}

	/**
	 * @param certPath
	 *            : the directory to the certificates files
	 * @param tempPath
	 *            : the directory to the temporarily saved files
	 * @param savePath
	 *            : the directory to the saved files
	 */
	public DictaoParamaters(String certPath, String tempPath, String savePath)
	{
		super();
		authority = "DICTAO";
		CertPath = certPath;
		TempPath = tempPath;
		SavePath = savePath;
		type = 2;
	}

	/**
	 * @return the certPath
	 */
	/*
	 * public String getCertPath() { return CertPath; }
	 *//**
	 * @param certPath
	 *            the certPath to set
	 */
	/*
	 * public void setCertPath(String certPath) { CertPath = certPath; }
	 *//**
	 * @return the tempPath
	 */
	/*
	 * public String getTempPath() { return TempPath; }
	 *//**
	 * @param tempPath
	 *            : the tempPath to set
	 */
	/*
	 * public void setTempPath(String tempPath) { TempPath = tempPath; }
	 *//**
	 * @return the savePath
	 */
	/*
	 * public String getSavePath() { return SavePath; }
	 *//**
	 * @param savePath
	 *            : the savePath to set
	 */
	/*
	 * public void setSavePath(String savePath) { SavePath = savePath; }
	 * 
	 * 
	 * (non-Javadoc)
	 * 
	 * @see domain.AuthorityParameters#getType()
	 * 
	 * @Override public int getType() { // TODO Auto-generated method stub
	 * return type; }
	 */
}
