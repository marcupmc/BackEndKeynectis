package tools;

import java.io.File;

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
		
		Element prenom = doc.createElement("prenom");
		prenom.setTextContent(user.getFirstName());
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
}
