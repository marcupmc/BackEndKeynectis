package tools;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.Text;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class ToolsXMLTemplate {


	public static Document buildXMLTemplate(long idClient){
		if(idClient<0)return null;
		Document docToReturn = createDOM();
		docToReturn = buildHeaderXMLTemplate(docToReturn);
		HashMap<String, String> hash = getInfoClient(idClient);
		String nom = hash.get("nom");
		String prenom = hash.get("prenom");
		docToReturn = buildXMLTemplateFromClient(docToReturn, nom,prenom);
		InputStream is = new ByteArrayInputStream(docToString(docToReturn).getBytes());
		
		return docToReturn;
	}

	public static HashMap<String, String> getInfoClient(long idClient){
		HashMap<String,String> hashMapToReturn = new HashMap<String, String>();
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String url = "jdbc:mysql://localhost/db";
			Connection conn = (Connection) DriverManager.getConnection(url, "root", "password");
			String query = "SELECT FIRSTNAME, LASTNAME FROM utilisateur WHERE ID_UTILISATEUR = "+idClient;
			Statement st = (Statement) conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			String prenom = null;
			String nom = null;
			while (rs.next())
			{
				 prenom = rs.getString("FIRSTNAME");
				 nom = rs.getString("LASTNAME");
			}
			hashMapToReturn.put("nom", nom);
			hashMapToReturn.put("prenom", prenom);
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return hashMapToReturn;
	}

	public static Document createDOM(){
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		try {
			builder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		Document docToReturn = builder.newDocument();
		return docToReturn;
	}

	public static Document buildHeaderXMLTemplate(Document doc){
		Element root = doc.createElement("edition_orange");
		doc.appendChild(root);

		Element header = doc.createElement("header");
		root.appendChild(header);

		Element footer = doc.createElement("footer");
		root.appendChild(footer);

		Element titleHeader = doc.createElement("title");
		titleHeader.setTextContent("DEMO SIGNATURE");
		header.appendChild(titleHeader);

		Element titleFooter = doc.createElement("title");
		titleFooter.setTextContent("SIGNATURE CLIENT");
		footer.appendChild(titleFooter);

		Element signature = doc.createElement("signature");
		footer.appendChild(signature);

		Element content = doc.createElement("content");
		root.appendChild(content);

		Element bloc_client = doc.createElement("bloc_client");
		content.appendChild(bloc_client);

		Element title = doc.createElement("title");
		title.setTextContent("INFORMATIONS CLIENT");
		bloc_client.appendChild(title);

		Element client = doc.createElement("client");
		bloc_client.appendChild(client);

		return doc;
	}

	public static Document buildXMLTemplateFromClient(Document doc,String nomClient, String prenomClient){
		Element client = (Element) doc.getElementsByTagName("client").item(0);

		Element nom = doc.createElement("nom");
		nom.setTextContent(nomClient);
		client.appendChild(nom);
		//Reprendre a partir du prenom

		Element prenom = doc.createElement("prenom");
		prenom.setTextContent(prenomClient);
		client.appendChild(prenom);

		Element adresse = doc.createElement("adresse");
		client.appendChild(adresse);

		Element adresseClient = doc.createElement("adresse");
		adresseClient.setTextContent("A REMPLIR");
		adresse.appendChild(adresseClient);

		Element codePostal = doc.createElement("cp");
		codePostal.setTextContent("A REMPLIR");
		adresse.appendChild(codePostal);

		Element ville = doc.createElement("ville");
		ville.setTextContent("A REMPLIR");
		adresse.appendChild(ville);

		Element pays = doc.createElement("pays");
		pays.setTextContent("A REMPLIR");
		adresse.appendChild(pays);

		return doc;
	}


	public static void docToFile(Document doc, String nomDeFichier){
		Source source = new DOMSource(doc);
		Result resultat = new StreamResult(new File(nomDeFichier));
		Transformer transfo = null;
		try {
			transfo = TransformerFactory.newInstance().newTransformer();
		} catch(TransformerConfigurationException e) {
			System.err.println("Impossible de creer un transformateur XML.");
			System.exit(1);
		}
		transfo.setOutputProperty(OutputKeys.METHOD, "xml");
		transfo.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
		transfo.setOutputProperty(OutputKeys.ENCODING, "utf-8");
		transfo.setOutputProperty(OutputKeys.INDENT, "yes");
		try {
			transfo.transform(source, resultat);
		} catch(TransformerException e) {
			System.err.println("La transformation a echoue : " + e);
			System.exit(1);
		}
	}
	
	/**
	 * transforme un element en String
	 * @param e
	 * @return
	 */
	public static String docToString(Element e)
	{
		String s = "";
		s+="<" + e.getNodeName() + "";
		NamedNodeMap attr = e.getAttributes();
		for(int i=0; i<attr.getLength(); i++) 
		{
			Attr a = (Attr)attr.item(i);
			s+= a.getName() + "=\"" + a.getNodeValue() + "\" ";
		}
		s+=">";

		for(Node n = e.getFirstChild(); n != null; n = n.getNextSibling()) 
		{
			switch(n.getNodeType()) {
			case Node.ELEMENT_NODE:
				s+=docToString((Element)n);
				break;
			case Node.TEXT_NODE:
				String data = ((Text)n).getData();
				s+=data;
				break;
			}
		}
		return s+"</" + e.getNodeName() + ">";
	}


	/**
	 * transforme un document en String
	 * @param doc
	 * @return
	 */
	public static String docToString(Document doc)
	{
		Element e = doc.getDocumentElement();
		return docToString(e);
	}
}
