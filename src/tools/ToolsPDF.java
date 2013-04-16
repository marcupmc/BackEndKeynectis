package tools;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import org.bouncycastle.util.encoders.Base64;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfAnnotation;
import com.itextpdf.text.pdf.PdfFormField;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfString;

public class ToolsPDF
{

	/**
	 * Lit un fichier (PDF) et le sauvegarde encodé en Base64 dans un fichier
	 * XML
	 * 
	 */
	public static String pdf2xml(String pdfFileName, String name,
			String pathServeur) throws IOException
	{
		System.out.println("SAVE FILE : " + pathServeur);
		byte[] fileArray = getBytesFromFile(new File(pdfFileName));

		// URL ur = new URL(pdfFileName);
		// byte[] fileArray =getAsByteArray(ur);
		if (fileArray != null)
		{
			String extension = ".xml";// or ".jpg" or anything
			String filename = pathServeur + "/" + name + extension;
			// String filename ="ressources/"+name+extension;
			System.out.println("FileName : " + filename);
			//byte[] data = fileArray;// the byte array which i got from server

			File f = new File(filename);

			try
			{
				byte[] beginTag = new String(
						"<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<docPDFb64>\n")
						.getBytes();
				byte[] endTag = new String("\n</docPDFb64>").getBytes();
				OutputStream os = new FileOutputStream(filename);

				os.write(beginTag);
				os.write(Base64.encode(fileArray));
				os.write(endTag);
				os.flush();
				os.close();

				System.out.println(f.getAbsolutePath());

				return f.getAbsolutePath();
			}
			catch (IOException e)
			{
				e.printStackTrace();
				return "";
			}

		}
		return "";
	}

	/**
	 * Retourne le contenu d'un fichier sous la forme d'un tableau de bytes
	 * 
	 */
	public static byte[] getBytesFromFile(File file) throws IOException
	{

		InputStream is = new FileInputStream(file);

		// Get the size of the file
		long length = file.length();

		if (length > Integer.MAX_VALUE)
		{
			is.close();
			throw new IOException("File is too large to process");
		}

		// Create the byte array to hold the data
		byte[] bytes = new byte[(int) length];

		// Read in the bytes
		int offset = 0;
		int numRead = 0;
		while ((offset < bytes.length)
				&& ((numRead = is.read(bytes, offset, bytes.length - offset)) >= 0))
		{
			offset += numRead;
		}

		// Ensure all the bytes have been read in
		if (offset < bytes.length)
		{
			is.close();
			throw new IOException("Could not completely read file "
					+ file.getName());
		}

		is.close();
		return bytes;
	}

	public static byte[] getAsByteArray(URL url) throws IOException
	{
		URLConnection connection = url.openConnection();
		// Since you get a URLConnection, use it to get the InputStream
		InputStream in = connection.getInputStream();
		// Now that the InputStream is open, get the content length
		int contentLength = connection.getContentLength();

		// To avoid having to resize the array over and over and over as
		// bytes are written to the array, provide an accurate estimate of
		// the ultimate size of the byte array
		ByteArrayOutputStream tmpOut;
		if (contentLength != -1)
		{
			tmpOut = new ByteArrayOutputStream(contentLength);
		}
		else
		{
			tmpOut = new ByteArrayOutputStream(16384); // Pick some appropriate
														// size
		}

		byte[] buf = new byte[512];
		while (true)
		{
			int len = in.read(buf);
			if (len == -1)
			{
				break;
			}
			tmpOut.write(buf, 0, len);
		}
		in.close();
		tmpOut.close(); // No effect, but good to do anyway to keep the metaphor
						// alive

		byte[] array = tmpOut.toByteArray();
		return array;

		// Lines below used to test if file is corrupt
		// FileOutputStream fos = new FileOutputStream("C:\\abc.pdf");
		// fos.write(array);
		// fos.close();

		// return ByteBuffer.wrap(array);
	}

	public static String createPDFDocToSign(String url, String pathFolderout,
			String name) throws DocumentException, IOException
	{
		String outFile = pathFolderout + "/" + name + ".pdf";

		try
		{
			PdfReader pdf = new PdfReader(url);
			PdfStamper stp = new PdfStamper(pdf, new FileOutputStream(outFile
			/* RESULT.substring(0, RESULT.lastIndexOf("/")) + "/out.pdf" */));
			PdfFormField sig = PdfFormField.createSignature(stp.getWriter());

			sig.setWidget(new Rectangle(100, 100, 200, 200), null);

			sig.setFlags(PdfAnnotation.FLAGS_PRINT);
			sig.put(PdfName.DA, new PdfString("/Helv 0 Tf 0 g"));
			sig.setFieldName("Signature1"); // TODO A changer et rendre
											// parametrable
			sig.setPage(1);
			stp.addAnnotation(sig, 1);
			stp.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

		return outFile;
	}
}
