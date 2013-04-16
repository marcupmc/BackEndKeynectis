package tools;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;

import sun.misc.BASE64Decoder;

public class EncoderBase64
{

	/**
	 * Convert a Blob type from a dataBase into an array of bytes
	 * 
	 * @param b
	 * @return and array of bytes
	 */
	public static byte[] encodingBlobToByteArray(Blob b)
	{

		// (assuming you have a ResultSet named RS)
		Blob blob = b;

		int blobLength;
		try
		{
			blobLength = (int) blob.length();
			byte[] blobAsBytes = blob.getBytes(1, blobLength);

			// release the blob and free up memory. (since JDBC 4.0)
			blob.free();
			return blobAsBytes;
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * Convert an array of byte into a string encode in base 64
	 * 
	 * @param bit
	 * @return a string encoded in base 64
	 */
	public static String byteArraytoStringBase64(byte[] bit)
	{
		sun.misc.BASE64Encoder encoder = new sun.misc.BASE64Encoder();
		return encoder.encode(bit);
	}

	/**
	 * Convert a string encoded in base64 into an array of bytes
	 * 
	 * @param s
	 * @return an array of bytes
	 */
	public static byte[] base64StringToByteArray(String s)
	{
		// Conversion en byte[64]
		BASE64Decoder decoder = new BASE64Decoder();
		byte[] decodedBytes = null;
		try
		{
			decodedBytes = decoder.decodeBuffer(s);
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return decodedBytes;
	}
}
