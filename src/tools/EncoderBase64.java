package tools;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;

import javax.imageio.ImageIO;

import org.eclipse.jdt.internal.compiler.ast.ThisReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import controller.ControllerCertification;
import domain.Log;
import domain.TypeLog;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

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
		final Marker marker = MarkerFactory.getMarker(TypeLog.ERREUR_DECODING_BLOB_SIGNATURE.toString());
		final Logger logger = LoggerFactory.getLogger(EncoderBase64.class);
		Log l = new Log();
		l.setIdentifiant_client("");
		l.setIpadresse(EncoderBase64.class.getName());

		Blob blob = b;

		int blobLength;
		try
		{
			blobLength = (int) blob.length();
			byte[] blobAsBytes = blob.getBytes(1, blobLength);
			blob.free();
			return blobAsBytes;
		}
		catch (SQLException e)
		{
			logger.info(marker, "Erreur de décodage du blob de la signature", l);
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
		BASE64Decoder decoder = new BASE64Decoder();
		byte[] decodedBytes = null;
		try
		{
			decodedBytes = decoder.decodeBuffer(s);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return decodedBytes;
	}

	/**
	 * Encode image to string
	 * 
	 * @param image
	 *            The image to encode
	 * @return encoded string
	 */
	public static String encodeToString(BufferedImage image)
	{
		String imageString = null;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();

		try
		{
			ImageIO.write(image, "jpg", bos);
			byte[] imageBytes = bos.toByteArray();

			BASE64Encoder encoder = new BASE64Encoder();
			imageString = encoder.encode(imageBytes);

			bos.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return imageString;
	}
}
