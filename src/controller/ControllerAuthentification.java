package controller;

public class ControllerAuthentification {

	//SINGLETON
	public static ControllerAuthentification getInstance() {
		if (null == instance) { // Premier appel
			instance = new ControllerAuthentification();
		}
		return instance;
	}

	/** Constructeur red�fini comme �tant priv� pour interdire
	 * son appel et forcer � passer par la m�thode <link
	 */
	private ControllerAuthentification() {
	}

	/** L'instance statique */
	private static ControllerAuthentification instance;

	
	// Methode de controle
	public boolean isAuthentified(String login, String password){
		// Fait appel au dao et compare les hasch�
		return false;
	}
	
}

