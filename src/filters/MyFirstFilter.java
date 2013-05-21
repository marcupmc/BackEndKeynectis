package filters;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;
import dao.DAOLog;
import dao.DAOUtilisateur;
import domain.Log;
import domain.TypeLog;

public class MyFirstFilter  extends Filter<ILoggingEvent> {


	@Override
	public FilterReply decide(ILoggingEvent arg0) {
		String typeName = arg0.getMarker().getName();
		Log l = (Log)arg0.getArgumentArray()[0];
		TypeLog typeToSave  = null;
		if(typeName.equals("CONNEXION_FAILED"))
			typeToSave=TypeLog.CONNEXION_FAILED;
		else if(typeName.equals("CONNEXION"))
			typeToSave=TypeLog.CONNEXION;
		else if(typeName.equals("DECONNEXION"))
			typeToSave=TypeLog.DECONNEXION;
		else if(typeName.equals("DEMANDE_SIGNATURE"))
			typeToSave=TypeLog.DEMANDE_SIGNATURE;
		else if(typeName.equals("CHANGEMENT_SIGNATURE"))
			typeToSave=TypeLog.CHANGEMENT_SIGNATURE;
		else if(typeName.equals("AJOUT_CLIENT"))
			typeToSave=TypeLog.AJOUT_CLIENT;
		else if(typeName.equals("SUPPRESSION_CLIENT"))
			typeToSave=TypeLog.SUPPRESSION_CLIENT;
		else if(typeName.equals("AJOUT_DOCUMENT"))
			typeToSave=TypeLog.AJOUT_DOCUMENT;
		else if(typeName.equals("SUPPRESSION_DOCUMENT"))
			typeToSave=TypeLog.SUPPRESSION_DOCUMENT;
		else if(typeName.equals("AJOUT_SIGNATURE"))
			typeToSave=TypeLog.AJOUT_SIGNATURE;
		DAOLog.getInstance().addLog(typeToSave, l.getIpadresse(),l.getIdentifiant_client());
		//TODO : A changer
		return null;
	}

}
