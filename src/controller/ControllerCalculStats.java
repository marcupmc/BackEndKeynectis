package controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import dao.DAODocumentPDF;
import dao.DAOLog;
import domain.EventType;
import domain.Log;
import domain.TypeLog;

public class ControllerCalculStats {

	// SINGLETON
	public static ControllerCalculStats getInstance()
	{
		if (null == instance)
		{ // Premier appel
			instance = new ControllerCalculStats();
		}
		return instance;
	}


	private ControllerCalculStats()
	{
	}

	private static ControllerCalculStats instance;

	public JSONObject getAllLogs(){
		JSONObject jsonToReturn =  new JSONObject();
		ArrayList<Log> logs = DAOLog.getInstance().getAllLogs();
		ArrayList<JSONObject> jsonOfAllLogs = new ArrayList<JSONObject>();
		try {
			for(Log l :logs){
				JSONObject jsonOfOneLog = new JSONObject();

				jsonOfOneLog.put("id",l.getId());
				jsonOfOneLog.put("date",l.getDate());
				jsonOfOneLog.put("type",l.getType().toString());
				jsonOfOneLog.put("ipadresse",l.getIpadresse());
				jsonOfOneLog.put("identifiant",l.getIdentifiant_client());
				jsonOfOneLog.put("event",l.getEventype().toString());

				jsonOfAllLogs.add(jsonOfOneLog);
			}
			jsonToReturn.put("logs", jsonOfAllLogs);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jsonToReturn;
	}

	public JSONObject getConnexionPerHour(){
		JSONObject json = new JSONObject();
		ArrayList<Log> logConnexion = DAOLog.getInstance().getLogPerType(TypeLog.CONNEXION);
		Map<Date, Integer> treeMap = new TreeMap<Date, Integer>();

		for(Log l :logConnexion)
		{
			Date temp = l.getDate();
			temp.setMinutes(0);
			temp.setSeconds(0);
			if(treeMap.containsKey(temp))treeMap.put(temp, treeMap.get(temp) + 1);
			else treeMap.put(temp, 1);
		}
		try {

			//			Date itDate = (Date) treeMap.keySet().toArray()[0];
			Date itDate = DAOLog.getInstance().getDateFromFirstLog();
			itDate.setMinutes(0);
			itDate.setSeconds(0);
			Date now = new Date();
			now.setMinutes(0);
			now.setSeconds(0);
			System.out.println(now.toGMTString());
			System.out.println(itDate.toGMTString());
			while(!itDate.toGMTString().equals(now.toGMTString())){
				if(!treeMap.containsKey(itDate)){
					treeMap.put(itDate, 0);
				}
				Calendar cal = Calendar.getInstance(); // creates calendar
				cal.setTime(itDate); // sets calendar time/date
				cal.add(Calendar.HOUR_OF_DAY, 1); // adds one hour
				itDate = cal.getTime(); // returns new date object, one hour in the future
			}

			for(Entry<Date, Integer> e :treeMap.entrySet()){
				json.put( e.getKey().toGMTString(),e.getValue());
			}


		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return json;
	}

	public JSONObject getConnexionReport(){
		ArrayList<Log> logConnexion = DAOLog.getInstance().getLogPerType(TypeLog.CONNEXION);
		ArrayList<Log> logConnexionFailed = DAOLog.getInstance().getLogPerType(TypeLog.CONNEXION_FAILED);
		JSONObject json =new JSONObject();
		try {
			json.put("success", logConnexion.size());
			json.put("failed",logConnexionFailed.size());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return json;
	}

	public JSONObject getErrorPerType(){
		ArrayList<Log> logErr = DAOLog.getInstance().getLogPerEventType(EventType.ERROR);
		int err1 = 0;
		int err2 = 0;
		int err3 = 0;
		int err4 = 0;
		int err5 = 0;
		int err6 = 0;
		int err7 = 0;
		int err8 = 0;
		int err9 = 0;
		int err10 = 0;
		for(Log l : logErr){
			switch(l.getType()){
			case CONNEXION_FAILED  : err1 ++;break;
			case ERREUR_LECTURE_CONFIGURATION : err2++;break;
			case ERREUR_HASHBASE64 : err3++;break;
			case ERREUR_GETBLOB : err4++;break;
			case ERREUR_DECODING_BLOB_SIGNATURE: err5++;break;
			case ERREUR_RTIFACTORY : err6++;break;
			case ERREUR_ORIGINAL_METIER_FACTORY : err7++;break;
			case ERREUR_ENCODING_PDF_SIGZONE : err8++;break;
			case ERREUR_PDF2XML : err9++; break;
			case ERREUR_KEYNECTIS_KWEBSIGN: err10++;break;
			default : break;
			}
		}

		JSONObject json =new JSONObject();
		try {
			json.put("CONNEXION_FAILED",err1);
			json.put("ERREUR_LECTURE_CONFIGURATION",err2);
			json.put("ERREUR_HASHBASE64", err3);
			json.put("ERREUR_GETBLOB", err4);
			json.put("ERREUR_DECODING_BLOB_SIGNATURE",err5);
			json.put("ERREUR_RTIFACTORY",err6);
			json.put("ERREUR_ORIGINAL_METIER_FACTORY", err7);
			json.put("ERREUR_ENCODING_PDF_SIGZONE", err8);
			json.put("ERREUR_PDF2XML", err9);
			json.put("ERREUR_KEYNECTIS_KWEBSIGN", err10);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return json;
	}

	public JSONObject getErrorPerHour(){
		JSONObject json = new JSONObject();
		ArrayList<Log> logConnexion = DAOLog.getInstance().getLogPerEventType(EventType.ERROR);//TODO: A changer
		Map<Date, Integer> treeMap = new TreeMap<Date, Integer>();

		for(Log l :logConnexion)
		{
			Date temp = l.getDate();
			//temp.setHours(1);
			temp.setMinutes(0);
			temp.setSeconds(0);
			if(treeMap.containsKey(temp))treeMap.put(temp, treeMap.get(temp) + 1);
			else treeMap.put(temp, 1);
		}
		try {


			//			Date itDate = (Date) treeMap.keySet().toArray()[0];
			Date itDate = DAOLog.getInstance().getDateFromFirstLog();
			itDate.setMinutes(0);
			itDate.setSeconds(0);
			Date now = new Date();
			now.setMinutes(0);
			now.setSeconds(0);
			while(!itDate.toGMTString().equals(now.toGMTString())){
				if(!treeMap.containsKey(itDate)){
					treeMap.put(itDate, 0);
				}
				Calendar cal = Calendar.getInstance(); // creates calendar
				cal.setTime(itDate); // sets calendar time/date
				cal.add(Calendar.HOUR_OF_DAY, 1); // adds one day
				itDate = cal.getTime(); // returns new date object, one hour in the future
			}

			for(Entry<Date, Integer> e :treeMap.entrySet()){
				json.put( e.getKey().toGMTString(),e.getValue());
			}


		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return json;
	}

	public JSONObject getDocumentStatutStats(){
		JSONObject jsonToReturn = new JSONObject();
		int nbDocCertifie = DAODocumentPDF.getInstance().getNumberOfCertifiedDocument();
		int nbDocErreur = DAODocumentPDF.getInstance().getNumberOfErrorDocument();
		int nbDocEnAttente = DAODocumentPDF.getInstance().getNumberOfWaitingDocument();
		try {
			jsonToReturn.put("certifie", nbDocCertifie);
			jsonToReturn.put("error",nbDocErreur);
			jsonToReturn.put("waiting", nbDocEnAttente);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jsonToReturn;
	}

}
