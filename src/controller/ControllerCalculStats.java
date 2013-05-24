package controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

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


			Date itDate = (Date) treeMap.keySet().toArray()[0];
			while(!itDate.equals(treeMap.keySet().toArray()[treeMap.size()-1])){
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
//		ArrayList<Log> logConnexion = DAOLog.getInstance().getLogPerType(TypeLog.CONNEXION);
//		ArrayList<Log> logConnexionFailed = DAOLog.getInstance().getLogPerType(TypeLog.CONNEXION_FAILED);
		JSONObject json =new JSONObject();
		try {
			json.put("failed",0);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return json;
	}
	
	public JSONObject getErrorPerDay(){
		JSONObject json = new JSONObject();
		ArrayList<Log> logConnexion = DAOLog.getInstance().getLogPerEventType(EventType.ADMIN);//TODO: A changer
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


			Date itDate = (Date) treeMap.keySet().toArray()[0];
			while(!itDate.equals(treeMap.keySet().toArray()[treeMap.size()-1])){
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


}
