/**
 * 
 */
package tools;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import domain.AuthorityParameters;
import domain.DictaoParamaters;
import domain.KeynectisParameters;

/**
 * @author dtadmi
 * 
 */
public class ToolsXML
{

	/**
	 * Create the xml file based on the certificate authority and the corresponding form informations
	 * 
	 * @param parameters
	 *            : the AuthorityParameters object, containing the informations
	 *            to save into xml
	 * @param savePath
	 *            : the directory where to save the xml file
	 */
	public static void createXMLFile(AuthorityParameters parameters,
			String savePath)
	{

		try
		{

			DocumentBuilderFactory docFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			// root elements
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("serverParameters");
			doc.appendChild(rootElement);

			switch (parameters.getType())
			{
				case 0:
				{
					System.out
							.println("Bad Parameter Type : 0! Choose between DICTAO and KWS authorities");
					break;
				}

				case 1:
				{
					System.out.println("ToolsXML/  type 1: KWS");

					KeynectisParameters param = (KeynectisParameters) parameters;

					// authority elements
					Element authority = doc.createElement("CertAuthority");
					rootElement.appendChild(authority);

					// set attribute to authority element
					Attr attr = doc.createAttribute("type");
					attr.setValue("1");
					authority.setAttributeNode(attr);

					authority.setAttribute("name", param.getAuthority());

					// shorten way
					// authority.setAttribute("id", "1");

					// certMetier elements
					Element certMetier = doc.createElement("certMetier");
					certMetier.appendChild(doc.createTextNode(param
							.getCertMetier()));
					authority.appendChild(certMetier);

					// mdpMetier elements
					Element mdpMetier = doc.createElement("mdpMetier");
					mdpMetier.appendChild(doc.createTextNode(param
							.getMdpMetier()));
					authority.appendChild(mdpMetier);

					// idAppMetier elements
					Element idAppMetier = doc.createElement("idAppMetier");
					idAppMetier.appendChild(doc.createTextNode(param
							.getIdAppMetier()));
					authority.appendChild(idAppMetier);

					// idServMetier elements
					Element idServMetier = doc.createElement("idServMetier");
					idServMetier.appendChild(doc.createTextNode(param
							.getIdServMetier()));
					authority.appendChild(idServMetier);

					// idOrgMetier elements
					Element idOrgMetier = doc.createElement("idOrgMetier");
					idOrgMetier.appendChild(doc.createTextNode(param
							.getIdOrgMetier()));
					authority.appendChild(idOrgMetier);

					// certSign elements
					Element certSign = doc.createElement("certSign");
					certSign.appendChild(doc.createTextNode(param.getCertSign()));
					authority.appendChild(certSign);

					// mdpCert elements
					Element mdpCert = doc.createElement("mdpCert");
					mdpCert.appendChild(doc.createTextNode(param.getMdpCert()));
					authority.appendChild(mdpCert);

					// certChiff elements
					Element certChiff = doc.createElement("certChiff");
					certChiff.appendChild(doc.createTextNode(param
							.getCertChiff()));
					authority.appendChild(certChiff);

					Element certDecipher = doc.createElement("certDecipher");
					certDecipher.appendChild(doc.createTextNode(param
							.getCertDecipher()));
					authority.appendChild(certDecipher);

					// mdpDecipher elements
					Element mdpDecipher = doc.createElement("mdpDecipher");
					mdpDecipher.appendChild(doc.createTextNode(param
							.getMdpDecipher()));
					authority.appendChild(mdpDecipher);

					// servPDFCert elements
					Element servPDFCert = doc.createElement("servPDFCert");
					servPDFCert.appendChild(doc.createTextNode(param
							.getServPDFCert()));
					authority.appendChild(servPDFCert);

					// pathPDFCert elements
					Element pathPDFCert = doc.createElement("pathPDFCert");
					pathPDFCert.appendChild(doc.createTextNode(param
							.getPathPDFCert()));
					authority.appendChild(pathPDFCert);

					// loginPDFCert elements
					Element loginPDFCert = doc.createElement("loginPDFCert");
					loginPDFCert.appendChild(doc.createTextNode(param
							.getLoginPDFCert()));
					authority.appendChild(loginPDFCert);

					Element mdpPDFCert = doc.createElement("mdpPDFCert");
					mdpPDFCert.appendChild(doc.createTextNode(param
							.getMdpPDFCert()));
					authority.appendChild(mdpPDFCert);

					break;
				}

				case 2:
				{
					System.out.println("ToolsXML/  type 2: DICTAO");

					DictaoParamaters param = (DictaoParamaters) parameters;

					// authority elements
					Element authority = doc.createElement("CertAuthority");
					rootElement.appendChild(authority);

					// set attribute to authority element
					Attr attr = doc.createAttribute("type");
					attr.setValue("2");
					authority.setAttributeNode(attr);

					authority.setAttribute("name", param.getAuthority());

					// shorten way
					// authority.setAttribute("id", "1");

					// CertPath elements
					Element CertPath = doc.createElement("CertPath");
					CertPath.appendChild(doc.createTextNode(param.getCertPath()));
					authority.appendChild(CertPath);

					// TempPath elements
					Element TempPath = doc.createElement("TempPath");
					TempPath.appendChild(doc.createTextNode(param.getTempPath()));
					authority.appendChild(TempPath);

					// SavePath elements
					Element SavePath = doc.createElement("SavePath");
					SavePath.appendChild(doc.createTextNode(param.getSavePath()));
					authority.appendChild(SavePath);

					break;
				}
			}

			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory
					.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);

			File xmlFile = new File(savePath + "\\parameters.xml");
			StreamResult result = new StreamResult(xmlFile);

			// Output to console for testing
			// StreamResult result = new StreamResult(System.out);

			transformer.transform(source, result);

			if (xmlFile.exists())
				System.out.println("File saved! " + xmlFile.getAbsolutePath());
			else
				System.out
						.println("Error while saving xml file: file not created!"
								+ xmlFile.getAbsolutePath());

		}
		catch (ParserConfigurationException pce)
		{
			pce.printStackTrace();
		}
		catch (TransformerException tfe)
		{
			tfe.printStackTrace();
		}
	}

}
