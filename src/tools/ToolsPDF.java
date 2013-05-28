package tools;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.bouncycastle.util.encoders.Base64;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfAnnotation;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfFormField;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfString;

import domain.DocumentPDF;
import domain.Signature;
import domain.Utilisateur;

public class ToolsPDF
{

	/**
	 * Read a pdf file and save it in Base64 in a xml file
	 * 
	 * @param pdfFileName
	 * @param name
	 * @param pathServeur
	 * @return the path of the xml file
	 * @throws IOException
	 */
	public static String pdf2xml(String pdfFileName, String name,
			String pathServeur) throws IOException
	{
		System.out.println("SAVE FILE : " + pathServeur);
		byte[] fileArray = getBytesFromFile(new File(pdfFileName));

		if (fileArray != null)
		{
			String extension = ".xml";
			String filename = pathServeur + "/" + name + extension;
			System.out.println("FileName : " + filename);
			// byte[] data = fileArray;
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
	 * Get the bytes from a file
	 * 
	 * @param file
	 * @return bytes array representing the file
	 * @throws IOException
	 */
	public static byte[] getBytesFromFile(File file) throws IOException
	{
		InputStream is = new FileInputStream(file);
		long length = file.length();

		if (length > Integer.MAX_VALUE)
		{
			is.close();
			throw new IOException("File is too large to process");
		}

		byte[] bytes = new byte[(int) length];

		int offset = 0;
		int numRead = 0;
		while ((offset < bytes.length)
				&& ((numRead = is.read(bytes, offset, bytes.length - offset)) >= 0))
		{
			offset += numRead;
		}
		if (offset < bytes.length)
		{
			is.close();
			throw new IOException("Could not completely read file "
					+ file.getName());
		}

		is.close();

		return bytes;
	}

	/**
	 * Get the bytes of files from an url
	 * 
	 * @param url
	 * @return bytes array that the file contains
	 * @throws IOException
	 */
	public static byte[] getAsByteArray(URL url) throws IOException
	{

		URLConnection connection = url.openConnection();
		connection.setConnectTimeout(10000);
		InputStream in = connection.getInputStream();
		int contentLength = connection.getContentLength();

		ByteArrayOutputStream tmpOut;
		if (contentLength != -1)
		{
			tmpOut = new ByteArrayOutputStream(contentLength);

		}
		else
		{
			tmpOut = new ByteArrayOutputStream(16384); // Pick some appropriate
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
		tmpOut.flush();
		tmpOut.close(); // No effect, but good to do anyway to keep the metaphor
		// alive

		byte[] array = tmpOut.toByteArray();
		return array;

	}

	/**
	 * Create a signature zone into a pdf
	 * 
	 * @param url
	 * @param pathFolderout
	 * @param name
	 * @param x
	 * @param y
	 * @param largeur
	 * @param hauteur
	 * @return the path of the new pdf
	 * @throws DocumentException
	 * @throws IOException
	 */
	public static String createPDFDocToSign(String pathFolderout,
			DocumentPDF document, byte[] decode) throws DocumentException,
			IOException
	{
		Signature first = null;
		ArrayList<Signature> listeImg = new ArrayList<Signature>();
		int i = 0;
		for (Signature signature : document.getSignatures())
		{
			if (i == 0)
				first = signature;
			else
				listeImg.add(signature);
			i++;
		}

		String outFile = "";
		if (listeImg.size() == 0)
		{
			outFile = pathFolderout + "/" + document.getName() + ".pdf";
		}
		else
			outFile = pathFolderout + "/" + document.getName() + "Temp.pdf";
		
		System.out.println("ToolsPDF: CreatePDFDocToSign\n pathFolderOut:"+pathFolderout+"end");
		System.out.println("\n");
		System.out.println("ToolsPDF: CreatePDFDocToSign\n outFile:"+outFile);
		
		try
		{
			PdfReader pdf = new PdfReader(EncoderBase64.encodingBlobToByteArray(document.getContenu()));
			
			FileOutputStream out = new FileOutputStream(outFile);
			PdfStamper stp = new PdfStamper(pdf, out);
			addSignature(stp, first, document.getOwner());

			stp.close();
			out.flush();
			out.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

		if (listeImg.size() > 0)
		{
			PdfReader pdf2 = new PdfReader(outFile);

			String outFile2 = pathFolderout + "/" + document.getName() + ".pdf";
			FileOutputStream out2 = new FileOutputStream(outFile2);
			PdfStamper stp2 = new PdfStamper(pdf2, out2);
			// byte[] bits = EncoderBase64.encodingBlobToByteArray
			// (document.getOwner().getSignature());
			for (Signature sigs : listeImg)
				addPicture(stp2, sigs, decode);
			stp2.close();
			out2.flush();
			out2.close();
			File f1 = new File(outFile);
			f1.delete();
			outFile = outFile2;
		}
		return outFile;
	}

	/**
	 * Add a signature zone into a PDF
	 * 
	 * @param stp
	 * @param signature
	 * @param user
	 */
	public static void addSignature(PdfStamper stp, Signature signature,
			Utilisateur user)
	{

		PdfFormField sig = PdfFormField.createSignature(stp.getWriter());

		sig.setWidget(
				new Rectangle(signature.getSignatureX(), (842 - signature
						.getSignatureY()), signature.getSignatureX()
						+ signature.getWidthSignature(), (842 - signature
						.getSignatureY()) + signature.getHeightSignature()),
				null);

		sig.setFlags(PdfAnnotation.FLAGS_PRINT);
		sig.put(PdfName.DA, new PdfString("/Helv 0 Tf 0 g"));
		sig.setFieldName(signature.getName());

		sig.setPage(signature.getPageNumber());

		stp.addAnnotation(sig, signature.getPageNumber());
	}

	/**
	 * Add the picture of the signature into the PDF
	 * 
	 * @param stp
	 * @param signature
	 * @param bits
	 */
	public static void addPicture(PdfStamper stp, Signature signature,
			byte[] bits)
	{
		PdfContentByte content = stp.getOverContent(signature.getPageNumber());
		Image img;
		try
		{
			img = Image.getInstance(bits);
			img.setAbsolutePosition(signature.getSignatureX(),
					842 - signature.getSignatureY());
			img.scaleAbsolute(signature.getWidthSignature(),
					signature.getHeightSignature());
			content.addImage(img);
		}
		catch (BadElementException e)
		{
			e.printStackTrace();
		}
		catch (MalformedURLException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		catch (DocumentException e)
		{
			e.printStackTrace();
		}

	}

	public static String createPDFDocToSignOLD(String url,
			String pathFolderout, String name, float x, float y, float largeur,
			float hauteur) throws DocumentException, IOException
	{
		String outFile = pathFolderout + "/" + name + ".pdf";

		try
		{
			PdfReader pdf = new PdfReader(url);
			PdfStamper stp = new PdfStamper(pdf, new FileOutputStream(outFile));

			PdfFormField sig = PdfFormField.createSignature(stp.getWriter());

			sig.setWidget(new Rectangle(x, (842 - y), x + largeur, (842 - y)
					+ hauteur), null);

			sig.setFlags(PdfAnnotation.FLAGS_PRINT);
			sig.put(PdfName.DA, new PdfString("/Helv 0 Tf 0 g"));
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

		return outFile;
	}

	/**
	 * Check if the signature zone "signame" exist in the pdf
	 * 
	 * @param url
	 * @param signame
	 * @return true if it exist, false if not
	 */
	public static boolean checkSignature(String url, String signame)
	{
		try
		{
			PdfReader pdf = new PdfReader(url);
			AcroFields af = pdf.getAcroFields();
			ArrayList<String> names = af.getSignatureNames();
			for (String nom : names)
			{
				if (signame.equals(nom))
					return true;
			}
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Deprecated
	/**
	 * Make a JSon object that contains the image of a page, and the page number
	 * @param url
	 * @param numPage
	 * @return
	 */
	public static JSONObject getInfosPDF(String url, int numPage)
	{
		JSONObject json = new JSONObject();
		try
		{
			json.put("image", getImageFromPDFPage(url, numPage));
			json.put("nbPages", getNbPageofPDF(url));
		}
		catch (JSONException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return json;
	}

	/**
	 * Make a JSon object that contains the image of a page, and the page number
	 * @param bits
	 * @param numPage
	 * @return
	 */
	public static JSONObject getInfosPDF(byte[] bits,int numPage){
		JSONObject json = new JSONObject();
		try
		{
			json.put("image", getImageFromPDFPage(bits, numPage));
			json.put("nbPages", getNbPageofPDF(bits));
		}
		catch (JSONException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return json;
	}
	
	/**
	 * Get the number of page of a pdf file from a byte array
	 * @param bits
	 * @return
	 */
	public static int getNbPageofPDF(byte[] bits)
	{
		try
		{
			InputStream is  =new ByteArrayInputStream(bits);
			PDDocument doc = PDDocument.load(is);
			int nb = doc.getDocumentCatalog().getAllPages().size();
			doc.close();
			doc = null;
			return nb;
		}
		catch (MalformedURLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return 0;
	}

	
	@Deprecated
	/**
	 * Get the number of page of a pdf file from it url
	 * @param url
	 * @return
	 */
	public static int getNbPageofPDF(String url)
	{
		try
		{
			PDDocument doc = PDDocument.load(new URL(url));
			int nb = doc.getDocumentCatalog().getAllPages().size();
			doc.close();
			doc = null;
			return nb;
		}
		catch (MalformedURLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return 0;
	}

	/**
	 * Get the image code of a pdf from a byte array and a page number
	 * @param bits
	 * @param numPage
	 * @return
	 */
	public static String getImageFromPDFPage(byte[] bits, int numPage)
	{
		try
		{
			InputStream is  =new ByteArrayInputStream(bits);
			PDDocument doc = PDDocument.load(is, true);
			PDPage page = (PDPage) doc.getDocumentCatalog().getAllPages()
					.get(numPage);
			BufferedImage im = page.convertToImage();

			doc.close();
			doc = null;
			return EncoderBase64.encodeToString(im);

		}
		catch (MalformedURLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "";
	}
	
	@Deprecated
	/**
	 * Get the image code of a pdf from a url and a page number
	 * @param url
	 * @param numPage
	 * @return
	 */
	public static String getImageFromPDFPage(String url, int numPage)
	{
		try
		{
			PDDocument doc = PDDocument.load(new URL(url), true);
			PDPage page = (PDPage) doc.getDocumentCatalog().getAllPages().get(numPage);
			BufferedImage im = page.convertToImage();
			doc.close();
			doc = null;
			return EncoderBase64.encodeToString(im);
		}
		catch (MalformedURLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}

}
