package dao;

public class DAODocumentPDF {

	//SINGLETON
			public static DAODocumentPDF getInstance() {
				if (null == instance) { 
					instance = new DAODocumentPDF();
				}
				return instance;
			}

			/** Constructeur redéfini comme étant privé pour interdire
			 * son appel et forcer à passer par la méthode <link
			 */
			private DAODocumentPDF() {
			}

			/** L'instance statique */
			private static DAODocumentPDF instance;
			
}
