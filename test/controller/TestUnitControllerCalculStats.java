package controller;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.TimeZone;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.junit.Test;

public class TestUnitControllerCalculStats {

	@Test
	public void TestConnexionReport() {
		JSONObject jsonReturn = ControllerCalculStats.getInstance().getConnexionReport();
		assertFalse(jsonReturn==null);
		assertTrue(jsonReturn.length()==2);
		assertTrue(jsonReturn.has("success"));
		assertTrue(jsonReturn.has("failed"));
		try {
			assertTrue(Integer.parseInt(jsonReturn.get("success").toString())>=0);
			assertTrue(Integer.parseInt(jsonReturn.get("failed").toString())>=0);
		} catch (NumberFormatException e) {
			assertTrue(false);
		} catch (JSONException e) {
			assertTrue(false);
		}
	}

	@Test
	public void TestGetErrorPerType(){
		JSONObject jsonReturn = ControllerCalculStats.getInstance().getErrorPerType();
		assertFalse(jsonReturn==null);
		assertTrue(jsonReturn.length()==10);
		assertTrue(jsonReturn.has("CONNEXION_FAILED"));
		assertTrue(jsonReturn.has("ERREUR_LECTURE_CONFIGURATION"));
		assertTrue(jsonReturn.has("ERREUR_HASHBASE64"));
		assertTrue(jsonReturn.has("ERREUR_GETBLOB"));
		assertTrue(jsonReturn.has("ERREUR_DECODING_BLOB_SIGNATURE"));
		assertTrue(jsonReturn.has("ERREUR_RTIFACTORY"));
		assertTrue(jsonReturn.has("ERREUR_ORIGINAL_METIER_FACTORY"));
		assertTrue(jsonReturn.has("ERREUR_ENCODING_PDF_SIGZONE"));
		assertTrue(jsonReturn.has("ERREUR_PDF2XML"));
		assertTrue(jsonReturn.has("ERREUR_KEYNECTIS_KWEBSIGN"));
		try {
			assertTrue(Integer.parseInt(jsonReturn.get("CONNEXION_FAILED").toString())>=0);
			assertTrue(Integer.parseInt(jsonReturn.get("ERREUR_LECTURE_CONFIGURATION").toString())>=0);
			assertTrue(Integer.parseInt(jsonReturn.get("ERREUR_HASHBASE64").toString())>=0);
			assertTrue(Integer.parseInt(jsonReturn.get("ERREUR_GETBLOB").toString())>=0);
			assertTrue(Integer.parseInt(jsonReturn.get("ERREUR_DECODING_BLOB_SIGNATURE").toString())>=0);
			assertTrue(Integer.parseInt(jsonReturn.get("ERREUR_RTIFACTORY").toString())>=0);
			assertTrue(Integer.parseInt(jsonReturn.get("ERREUR_ORIGINAL_METIER_FACTORY").toString())>=0);
			assertTrue(Integer.parseInt(jsonReturn.get("ERREUR_ENCODING_PDF_SIGZONE").toString())>=0);
			assertTrue(Integer.parseInt(jsonReturn.get("ERREUR_PDF2XML").toString())>=0);
			assertTrue(Integer.parseInt(jsonReturn.get("ERREUR_KEYNECTIS_KWEBSIGN").toString())>=0);
		} catch (NumberFormatException e) {
			assertTrue(false);
		} catch (JSONException e) {
			assertTrue(false);
		}
	}

	@Test
	public void TestGetErrorPerHour(){
		JSONObject jsonReturn = ControllerCalculStats.getInstance().getErrorPerHour();
		assertFalse(jsonReturn==null);
		assertTrue(jsonReturn.length()>0);

		//Test du format de la clef
		Iterator<?> keys = jsonReturn.keys();
		String firstKey = (String) keys.next();
		assertTrue(firstKey.length()>0);
		//TODO : completer ce test
		//		DateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy hh:mm:ss GMT");
		//		Date dateFromString;
		//		try {
		//			dateFromString = dateFormat.parse(firstKey);
		//			assertFalse(dateFromString==null);
		//		} catch (ParseException e) {
		//			e.printStackTrace();
		//			assertTrue(false);
		//		}

		//Test du format de la value
		try {
			assertFalse(jsonReturn.get(firstKey)==null);
			assertTrue(Integer.parseInt(jsonReturn.getString(firstKey))>=0);
		} catch (JSONException e) {
			assertTrue(false);
		}
	}

	@Test
	public void TestUnitGetConnexionPerHour(){
		JSONObject jsonReturn = ControllerCalculStats.getInstance().getConnexionPerHour();
		assertFalse(jsonReturn==null);
		assertTrue(jsonReturn.length()>=0);

		//Test du format de la clef
		Iterator<?> keys = jsonReturn.keys();
		String firstKey = (String) keys.next();
		assertTrue(firstKey.length()>0);
		try {
			assertFalse(jsonReturn.get(firstKey)==null);
			assertTrue(Integer.parseInt(jsonReturn.getString(firstKey))>=0);
		} catch (JSONException e) {
			assertTrue(false);
		}
	}


}
