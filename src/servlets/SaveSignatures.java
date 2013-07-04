package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import dao.DAODocumentPDF;
import domain.Log;
import domain.TypeLog;

/**
 * Servlet implementation class SaveSignatures
 */
public class SaveSignatures extends HttpServlet
{

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SaveSignatures()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
		long idDoc = Long.parseLong(request.getParameter("idDoc"));

		float dimX = Float.parseFloat(request.getParameter("dimX"));
		float dimY = Float.parseFloat(request.getParameter("dimY"));

		float pdfX = Float.parseFloat(request.getParameter("pdfX"));
		float pdfY = Float.parseFloat(request.getParameter("pdfY"));

		String json = request.getParameter("jsondata");

		JSONArray list = null;
		try
		{
			// signatures = new JSON
			list = new JSONArray(json);

		}
		catch (JSONException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i < list.length(); i++)
		{
			try
			{
				JSONObject jobj = list.getJSONObject(i);
				saveSignatureFromJSON(idDoc, jobj, dimX, dimY, pdfX, pdfY);
			}
			catch (JSONException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		final Marker marker = MarkerFactory.getMarker(TypeLog.AJOUT_SIGNATURE
				.toString());
		final Logger logger = LoggerFactory.getLogger(SaveSignatures.class);
		Log l = new Log();
		l.setIdentifiant_client(DAODocumentPDF.getInstance().getById(idDoc)
				.getOwner().getIdentifiant());
		l.setIpadresse(request.getServerName());
		logger.info(marker, "Ajout d'une zone de signature", l);

		response.getWriter().write("success");
	}

	private boolean saveSignatureFromJSON(long idDoc, JSONObject jobj,
			float dimX, float dimY, float pdfX, float pdfY)
	{
		try
		{
			// float tempX =
			// (Float.parseFloat(jobj.get("posx").toString()))-pdfX;
			// float tempY =
			// (Float.parseFloat(jobj.get("posy").toString()))-pdfY;
			//
			// double tempX =
			// (Double.parseDouble(jobj.get("posx").toString()))-pdfX;
			// double tempY =
			// (Double.parseDouble(jobj.get("posy").toString()))-pdfY;
			//
			// float realX = (float) ((tempX*596)/dimX);
			// float realY = (float) ((tempY*842)/dimY);
			//
			// float realLargeur =(
			// Float.parseFloat(jobj.get("largeur").toString())*596)/dimX;
			// float realHauteur =(
			// Float.parseFloat(jobj.get("hauteur").toString())*842)/dimY;

			// En dure
			float tempX = (Float.parseFloat(jobj.get("posx").toString())) - 343;
			float tempY = (Float.parseFloat(jobj.get("posy").toString())) - 50;// -91;

			float realX = (float) ((tempX * 596) / 600);
			float realY = (float) ((tempY * 842) / 800);

			float realLargeur = (Float.parseFloat(jobj.get("largeur")
					.toString()) * 596) / 600;
			float realHauteur = (Float.parseFloat(jobj.get("hauteur")
					.toString()) * 842) / 800;

			System.out.println("tempX : " + tempX + " | tempY : " + tempY
					+ "\n" + "realX : " + realX + " | realY : " + realY + "\n"
					+ "realLargeur : " + realLargeur + " | realHauteur : "
					+ realHauteur);

			return DAODocumentPDF.getInstance().setPosSignature(idDoc, realX,
					realY, realLargeur, realHauteur,
					Integer.parseInt(jobj.get("num").toString()) + 1,
					jobj.get("nom").toString());
		}
		catch (NumberFormatException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		catch (JSONException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return false;
	}

}
