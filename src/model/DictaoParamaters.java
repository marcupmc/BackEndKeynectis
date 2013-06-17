/**
 * 
 */
package model;

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
}
