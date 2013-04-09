package controller;

import java.util.ArrayList;

import dao.DAOUtilisateur;

public class ControllerCertification {

	
	//SINGLETON
		public static ControllerCertification getInstance() {
			if (null == instance) { // Premier appel
				instance = new ControllerCertification();
			}
			return instance;
		}

		/** Constructeur red�fini comme �tant priv� pour interdire
		 * son appel et forcer � passer par la m�thode <link
		 */
		private ControllerCertification() {
		}

		/** L'instance statique */
		private static ControllerCertification instance;
		
		public boolean certificationPDF(String identifiant, ArrayList<String> urls){
			if(identifiant ==null||identifiant.length()==0)return false;
			if(urls==null || urls.size()==0)return false;
			boolean isOk=true;
			for(String url : urls){
				if(!DAOUtilisateur.getInstance().certifiedDocument(identifiant,url))
					isOk=false;
			}
			return isOk;
		}
}
