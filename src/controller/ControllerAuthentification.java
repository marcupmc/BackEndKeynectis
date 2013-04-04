package controller;

public class ControllerAuthentification {

	//SINGLETON
	public static ControllerAuthentification getInstance() {
		if (null == instance) { // Premier appel
			instance = new ControllerAuthentification();
		}
		return instance;
	}

	/** Constructeur redéfini comme étant privé pour interdire
	 * son appel et forcer à passer par la méthode <link
	 */
	private ControllerAuthentification() {
	}

	/** L'instance statique */
	private static ControllerAuthentification instance;

	
	// Methode de controle
	public boolean isAuthentified(String login, String password){
		// Fait appel au dao et compare les hasché
		return false;
	}
	
}

