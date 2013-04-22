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
import java.util.HashMap;
import java.util.List;

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
			// byte[] data = fileArray;// the byte array which i got from server

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

	/**
	 * Prepare a PDF document for the signature process
	 * 
	 * @param PDF2Sign
	 *            : the of the PDF document to prepare
	 * @param DataMetier
	 *            : the tag parameter DATA_METIER; set by default to
	 *            "PDFDocument"
	 * @param CufOrg
	 *            : the tag parameter CUF_ORG; set by default to "no"
	 * @param PDFSignField
	 *            : the name of the inserted Signature field; by default it is
	 *            the name of the document
	 * @return a HashMap containing the DataMetier tag, the CufOrg tag, the name
	 *         of the signature field and the path of the prepared document
	 * @throws DocumentException
	 * @throws IOException
	 */
	public static HashMap<String, String> preparePDFDocument(String PDF2Sign,
			String DataMetier, String CufOrg, String PDFSignField)
			throws DocumentException, IOException
	{
		HashMap<String, String> result = new HashMap<String, String>();

		/**
		 * §DEFINITION DES PARAMETRES PAR DEFAUT§ *
		 * **/
		String defaultMetier = "PDFDocument";

		String defaultSignField = PDF2Sign.substring(
				PDF2Sign.lastIndexOf("/") + 1, PDF2Sign.lastIndexOf("."));

		result.put("DATA_METIER", defaultMetier);
		result.put("CUF_ORG", "no");
		result.put("PDF_SIGN_FIELD", defaultSignField);

		String outFile = PDF2Sign.substring(0, PDF2Sign.lastIndexOf("."))
				+ "_out.pdf";

		result.put("OUTFILE", outFile);

		/** *§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§* **/

		try
		{
			PdfReader pdf = new PdfReader(PDF2Sign);
			PdfStamper stp = new PdfStamper(pdf, new FileOutputStream(outFile
			/* RESULT.substring(0, RESULT.lastIndexOf("/")) + "/out.pdf" */));

			/**
			 * §PARAMETRAGE DE LA ZONE DE SIGNATURE§ *
			 * **/

			if (null != stp.getSignatureAppearance().getFieldName())
			{
				if (null != PDFSignField) // Case where there is an embedded pdf
											// signature field
				{
					if (stp.getSignatureAppearance().getFieldName() == PDFSignField)
					{
						PDFSignField = stp.getSignatureAppearance()
								.getFieldName();
						result.put("PDF_SIGN_FIELD", PDFSignField);
					}
				}
			}
			else
			{
				PdfFormField sig = PdfFormField
						.createSignature(stp.getWriter());
				sig.setWidget(new Rectangle(100, 100, 200, 200), null);
				sig.setFlags(PdfAnnotation.FLAGS_PRINT);
				sig.put(PdfName.DA, new PdfString("/Helv 0 Tf 0 g"));
				if (null != PDFSignField)
					sig.setFieldName(PDFSignField);
				else
					sig.setFieldName(defaultSignField);
				sig.setPage(1);
				stp.addAnnotation(sig, 1);
			}
			stp.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

		/** *§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§* **/

		result.put("PDF_FILE_NAME", outFile);
		if (null != DataMetier)
			result.put("DATA_METIER", DataMetier);
		if (null != CufOrg)
			result.put("CUF_ORG", CufOrg);

		return result;
	}

	/**
	 * Prepare a PDF document for the signature process
	 * 
	 * @param PDF2Sign
	 *            : the url of the PDF document to prepare
	 * @param DataMetier
	 *            : the tag parameter DATA_METIER; set by default to
	 *            "PDFDocument"
	 * @param CufOrg
	 *            : the tag parameter CUF_ORG; set by default to "no"
	 * @param OutPath
	 *            : the path to the output directory
	 * @param PDFSignField
	 *            : the name of the inserted Signature field; by default it is
	 *            the name of the document
	 * @return a HashMap containing the DataMetier tag, the CufOrg tag, the name
	 *         of the signature field and the path of the prepared document
	 * @throws DocumentException
	 * @throws IOException
	 */
	public static HashMap<String, String> preparePDFDocument(String PDF2Sign,
			String DataMetier, String CufOrg, String PDFSignField,
			String OutPath) throws DocumentException, IOException
	{
		HashMap<String, String> result = new HashMap<String, String>();

		/**
		 * §DEFINITION DES PARAMETRES PAR DEFAUT§ *
		 * **/
		String defaultMetier = "PDFDocument";

		String defaultSignField = PDF2Sign.substring(
				PDF2Sign.lastIndexOf("/") + 1, PDF2Sign.lastIndexOf("."));

		result.put("DATA_METIER", defaultMetier);
		result.put("CUF_ORG", "no");
		result.put("PDF_SIGN_FIELD", defaultSignField);

		String outFile = OutPath + "/" + defaultSignField + ".pdf";

		result.put("OUTFILE", outFile);

		/** *§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§* **/

		try
		{
			PdfReader pdf = new PdfReader(PDF2Sign);
			PdfStamper stp = new PdfStamper(pdf, new FileOutputStream(outFile
			/* RESULT.substring(0, RESULT.lastIndexOf("/")) + "/out.pdf" */));

			/**
			 * §PARAMETRAGE DE LA ZONE DE SIGNATURE§ *
			 * **/

			if (null != stp.getSignatureAppearance().getFieldName())
			{
				if (null != PDFSignField) // Case where there is an embedded pdf
											// signature field
				{
					if (stp.getSignatureAppearance().getFieldName() == PDFSignField)
					{
						PDFSignField = stp.getSignatureAppearance()
								.getFieldName();
						result.put("PDF_SIGN_FIELD", PDFSignField);
					}
				}
			}
			else
			{
				PdfFormField sig = PdfFormField
						.createSignature(stp.getWriter());
				sig.setWidget(new Rectangle(100, 100, 200, 200), null);
				sig.setFlags(PdfAnnotation.FLAGS_PRINT);
				sig.put(PdfName.DA, new PdfString("/Helv 0 Tf 0 g"));
				if (null != PDFSignField)
					sig.setFieldName(PDFSignField);
				else
					sig.setFieldName(defaultSignField);
				sig.setPage(1);
				stp.addAnnotation(sig, 1);
			}
			stp.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

		/** *§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§* **/

		result.put("PDF_FILE_NAME", outFile);
		if (null != DataMetier)
			result.put("DATA_METIER", DataMetier);
		if (null != CufOrg)
			result.put("CUF_ORG", CufOrg);

		return result;
	}

	/**
	 * Prepare a PDF document with the given position of the signature field,
	 * for the signature process
	 * 
	 * @param PDF2Sign
	 *            : the PDF document to prepare
	 * @param DataMetier
	 *            : the tag parameter DATA_METIER; set by default to
	 *            "PDFDocument"
	 * @param CufOrg
	 *            : the tag parameter CUF_ORG; set by default to "no"
	 * @param x
	 *            : the x position of the origin of the signature field
	 * @param y
	 *            : the y position of the origin of the signature field
	 * @param height
	 *            : the height of the signature field
	 * @param width
	 *            : the width of the signature field
	 * @return a HashMap containing the DataMetier tag, the CufOrg tag, the name
	 *         of the signature field and the path of the prepared document
	 */
	public static HashMap<String, String> preparePDFDocument(String PDF2Sign,
			String DataMetier, String CufOrg, float x, float y, float height,
			float width) throws DocumentException, IOException
	{
		HashMap<String, String> result = new HashMap<String, String>();

		/**
		 * §DEFINITION DES PARAMETRES PAR DEFAUT§ *
		 * **/
		String defaultMetier = "PDFDocument";
		String defaultSignField = PDF2Sign.substring(
				PDF2Sign.lastIndexOf("/") + 1, PDF2Sign.lastIndexOf("."));

		result.put("DATA_METIER", defaultMetier);
		result.put("CUF_ORG", "no");
		result.put("PDF_SIGN_FIELD", defaultSignField);

		String outFile = PDF2Sign.substring(0, PDF2Sign.lastIndexOf("."))
				+ "_out.pdf";

		result.put("OUTFILE", outFile);

		/** *§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§* **/

		try
		{
			PdfReader pdf = new PdfReader(PDF2Sign);
			PdfStamper stp = new PdfStamper(pdf, new FileOutputStream(outFile
			/* RESULT.substring(0, RESULT.lastIndexOf("/")) + "/out.pdf" */));

			/**
			 * §PARAMETRAGE DE LA ZONE DE SIGNATURE§ *
			 * **/

			PdfFormField sig = PdfFormField.createSignature(stp.getWriter());
			sig.setWidget(new Rectangle(x, y, width, height), null);
			sig.setFlags(PdfAnnotation.FLAGS_PRINT);
			sig.put(PdfName.DA, new PdfString("/Helv 0 Tf 0 g"));
			sig.setFieldName(defaultSignField);
			sig.setPage(1);
			stp.addAnnotation(sig, 1);
			stp.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

		/** *§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§* **/

		result.put("PDF_FILE_NAME", outFile);
		if (null != DataMetier)
			result.put("DATA_METIER", DataMetier);
		if (null != CufOrg)
			result.put("CUF_ORG", CufOrg);

		return result;
	}

	/**
	 * Prepare a PDF document with the given position of the signature field,
	 * for the signature process
	 * 
	 * @param PDF2Sign
	 *            : the PDF document to prepare
	 * @param DataMetier
	 *            : the tag parameter DATA_METIER; set by default to
	 *            "PDFDocument"
	 * @param CufOrg
	 *            : the tag parameter CUF_ORG; set by default to "no"
	 * @param x
	 *            : the x position of the origin of the signature field
	 * @param y
	 *            : the y position of the origin of the signature field
	 * @param height
	 *            : the height of the signature field
	 * @param width
	 *            : the width of the signature field
	 * @param OutPath
	 *            : the path to the output directory
	 * @return a HashMap containing the DataMetier tag, the CufOrg tag, the name
	 *         of the signature field and the path of the prepared document
	 * 
	 * 
	 * 
	 * @throws DocumentException
	 * @throws IOException
	 */
	public static HashMap<String, String> preparePDFDocument(String PDF2Sign,
			String DataMetier, String CufOrg, float x, float y, float height,
			float width, String OutPath) throws DocumentException, IOException
	{
		HashMap<String, String> result = new HashMap<String, String>();

		/**
		 * §DEFINITION DES PARAMETRES PAR DEFAUT§ *
		 * **/
		String defaultMetier = "PDFDocument";
		String defaultSignField = PDF2Sign.substring(
				PDF2Sign.lastIndexOf("/") + 1, PDF2Sign.lastIndexOf("."));

		result.put("DATA_METIER", defaultMetier);
		result.put("CUF_ORG", "no");
		result.put("PDF_SIGN_FIELD", defaultSignField);

		String outFile = OutPath + "/" + defaultSignField + ".pdf";

		result.put("OUTFILE", outFile);

		/** *§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§* **/

		try
		{
			PdfReader pdf = new PdfReader(PDF2Sign);
			PdfStamper stp = new PdfStamper(pdf, new FileOutputStream(outFile
			/* RESULT.substring(0, RESULT.lastIndexOf("/")) + "/out.pdf" */));

			/**
			 * §PARAMETRAGE DE LA ZONE DE SIGNATURE§ *
			 * **/

			PdfFormField sig = PdfFormField.createSignature(stp.getWriter());
			sig.setWidget(new Rectangle(x, y, width, height), null);
			sig.setFlags(PdfAnnotation.FLAGS_PRINT);
			sig.put(PdfName.DA, new PdfString("/Helv 0 Tf 0 g"));
			//sig.setFieldName(defaultSignField);
			sig.setFieldName("Signature1");
			sig.setPage(1);
			stp.addAnnotation(sig, 1);
			stp.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

		/** *§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§* **/

		result.put("PDF_FILE_NAME", outFile);
		if (null != DataMetier)
			result.put("DATA_METIER", DataMetier);
		if (null != CufOrg)
			result.put("CUF_ORG", CufOrg);

		return result;
	}

	/**
	 * Prepare a PDF document with a list of signature fields for the signature
	 * process
	 * 
	 * @param PDF2Sign
	 *            : the PDF document to prepare
	 * @param DataMetier
	 *            : the tag parameter DATA_METIER; set by default to
	 *            "PDFDocument"
	 * @param CufOrg
	 *            : the tag parameter CUF_ORG; set by default to "no"
	 * @param PDFSignFields
	 *            : a list of the inserted Signature fields; if null, a single
	 *            field name as the document will be inserted
	 * @return a HashMap containing the DataMetier tag, the CufOrg tag, the name
	 *         of the signature field and the path of the prepared document
	 */
	public static HashMap<String, String> preparePDFDocument(String PDF2Sign,
			String DataMetier, String CufOrg, List<String> PDFSignFields)
			throws DocumentException, IOException
	{
		HashMap<String, String> result = new HashMap<String, String>();

		/**
		 * §DEFINITION DES PARAMETRES PAR DEFAUT§ *
		 * **/
		String defaultMetier = "PDFDocument";
		String defaultSignField = PDF2Sign.substring(
				PDF2Sign.lastIndexOf("/") + 1, PDF2Sign.lastIndexOf("."));

		result.put("DATA_METIER", defaultMetier);
		result.put("CUF_ORG", "no");
		result.put("PDF_SIGN_FIELD", defaultSignField);

		String outFile = PDF2Sign.substring(0, PDF2Sign.lastIndexOf("."))
				+ "_out.pdf";

		result.put("OUTFILE", outFile);

		/** *§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§* **/

		String signField = null;

		try
		{
			PdfReader pdf = new PdfReader(PDF2Sign);
			PdfStamper stp = new PdfStamper(pdf, new FileOutputStream(outFile
			/* RESULT.substring(0, RESULT.lastIndexOf("/")) + "/out.pdf" */));

			/**
			 * §PARAMETRAGE DES ZONES DE SIGNATURE§ *
			 * **/

			// Problème: comment récupérer l'ensemble des noms de signature
			// fields un à un???

			int cpt = 0, size = PDFSignFields.size();

			for (String PDFSignField : PDFSignFields)
			{
				cpt++;
				if (null != stp.getSignatureAppearance().getFieldName()) // !!!
																			// Certainement
																			// pas
																			// bon!!!
																			// Récupère
																			// une
																			// seule
																			// zone
																			// à
																			// priori
				{
					if (null != PDFSignField)
					{
						if (stp.getSignatureAppearance().getFieldName() == PDFSignField)
						{
							PDFSignField = stp.getSignatureAppearance()
									.getFieldName();
							signField += PDFSignField;
							// result.put("PDF_SIGN_FIELD", PDFSignField + ":");
						}
					}
				}
				else
				{
					PdfFormField sig = PdfFormField.createSignature(stp
							.getWriter());
					sig.setWidget(new Rectangle(100, 100, 200, 200), null);
					sig.setFlags(PdfAnnotation.FLAGS_PRINT);
					sig.put(PdfName.DA, new PdfString("/Helv 0 Tf 0 g"));
					if (null != PDFSignField)
					{
						sig.setFieldName(PDFSignField);
						signField += PDFSignField;
					}
					else
					{
						sig.setFieldName(defaultSignField + cpt);
						signField += defaultSignField + cpt;
					}
					sig.setPage(1);
					stp.addAnnotation(sig, 1);
				}

				if (size != cpt)
					signField += ": ";
			}
			stp.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

		result.put("PDF_SIGN_FIELD", signField);

		result.put("PDF_FILE_NAME", outFile);
		if (null != DataMetier)
			result.put("DATA_METIER", DataMetier);
		if (null != CufOrg)
			result.put("CUF_ORG", CufOrg);

		return result;
	}

}
