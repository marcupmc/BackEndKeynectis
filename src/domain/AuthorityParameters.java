/**
 * 
 */
package domain;

/**
 * @author dtadmi
 * 
 */
public class AuthorityParameters
{

	protected String authority = "";
	protected int type = 0;

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

}
