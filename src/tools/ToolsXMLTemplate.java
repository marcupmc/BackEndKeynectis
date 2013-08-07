package tools;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import dao.DAOUtilisateur;
import domain.Utilisateur;

public class ToolsXMLTemplate {

	
	public static Document buildXMLTemplate(long idClient){
		if(idClient<0)return null;
		Document docToReturn = createDOM();
		docToReturn = buildHeaderXMLTemplate(docToReturn);
		Utilisateur user = DAOUtilisateur.getInstance().getUserById(idClient);
		if(user==null)return null;
		docToReturn = buildXMLTemplateFromClient(docToReturn, user);
		return docToReturn;
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
	
	public static Document buildXMLTemplateFromClient(Document doc,Utilisateur user){
		Element client = (Element) doc.getElementsByTagName("client").item(0);
		
		Element nom = doc.createElement("nom");
		nom.setTextContent(user.getLastName());
		client.appendChild(nom);
		//Reprendre a partir du prenom
		
		
		return doc;
	}
}
