package dao;

public class DAODocumentPDF {

	//SINGLETON
			public static DAODocumentPDF getInstance() {
				if (null == instance) { 
					instance = new DAODocumentPDF();
				}
				return instance;
			}

			/** Constructeur red�fini comme �tant priv� pour interdire
			 * son appel et forcer � passer par la m�thode <link
			 */
			private DAODocumentPDF() {
			}

			/** L'instance statique */
			private static DAODocumentPDF instance;
			
}
