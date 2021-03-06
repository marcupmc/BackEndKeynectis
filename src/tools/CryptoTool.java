package tools;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CryptoTool
{

	/**
	 * Hash a string into MD5 code
	 * 
	 * @param key
	 * @return MD5 encoding of a string
	 * @throws NoSuchAlgorithmException
	 */
	public static String getEncodedPassword(String key)
			throws NoSuchAlgorithmException
	{
		if (key == null || key.length() == 0)
			return "";

		byte[] uniqueKey = key.getBytes();
		byte[] hash = null;
		hash = MessageDigest.getInstance("MD5").digest(uniqueKey);
		StringBuffer hashString = new StringBuffer();
		for (int i = 0; i < hash.length; ++i)
		{
			String hex = Integer.toHexString(hash[i]);
			if (hex.length() == 1)
			{
				hashString.append('0');
				hashString.append(hex.charAt(hex.length() - 1));
			}
			else
			{
				hashString.append(hex.substring(hex.length() - 2));
			}
		}
		return hashString.toString();
	}
}
