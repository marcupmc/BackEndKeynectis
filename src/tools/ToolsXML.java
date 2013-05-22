/**
 * 
 */
package tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
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

	static final String KEY = "KWS_INTEGRATION_CDS";
	static final String DICTAO = "DICTAO";

	static final String CERTAUTHORITY = "certAuthority";
	static final String NAME = "name";
	static final String TYPE = "type";

	static final String CERTMETIER = "certMetier";
	static final String MDPMETIER = "mdpMetier";
	static final String IDAPPMETIER = "idAppMetier";
	static final String IDSERVMETIER = "idServMetier";
	static final String IDORGMETIER = "idOrgMetier";

	static final String CERTSIGN = "certSign";
	static final String MDPCERT = "mdpCert";

	static final String CERTCHIFF = "certChiff";

	static final String CERTDECIPHER = "certDecipher";
	static final String MDPDECIPHER = "mdpDecipher";

	static final String SERVPDFCERT = "servPDFCert";
	static final String PATHPDFCERT = "pathPDFCert";
	static final String LOGINPDFCERT = "loginPDFCert";
	static final String MDPPDFCERT = "mdpPDFCert";

	static final String CERTPATH = "CertPath";
	static final String TEMPPATH = "TempPath";
	static final String SAVEPATH = "SavePath";

	/**
	 * Create the xml file based on the certificate authority and the
	 * corresponding form informations
	 * 
	 * @param parameters
	 *            : the AuthorityParameters object, containing the informations
	 *            to save into xml
	 * @param savePath
	 *            : the directory where to save the xml file
	 */
	public static boolean createXMLFile(AuthorityParameters parameters,
			String savePath)
	{
		boolean created = false;

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
					Element authority = doc.createElement(CERTAUTHORITY);
					rootElement.appendChild(authority);

					// set attribute to authority element
					Attr attr = doc.createAttribute(TYPE);
					attr.setValue("1");
					authority.setAttributeNode(attr);

					authority.setAttribute(NAME, param.getAuthority());

					// shorten way
					// authority.setAttribute("id", "1");

					// CertPath elements
					Element CertPath = doc.createElement(CERTPATH);
					CertPath.appendChild(doc.createTextNode(param.getCertPath()));
					authority.appendChild(CertPath);

					// TempPath elements
					Element TempPath = doc.createElement(TEMPPATH);
					TempPath.appendChild(doc.createTextNode(param.getTempPath()));
					authority.appendChild(TempPath);

					// SavePath elements
					Element SavePath = doc.createElement(SAVEPATH);
					SavePath.appendChild(doc.createTextNode(param.getSavePath()));
					authority.appendChild(SavePath);

					// certMetier elements
					Element certMetier = doc.createElement(CERTMETIER);
					certMetier.appendChild(doc.createTextNode(param
							.getCertMetier()));
					authority.appendChild(certMetier);

					// mdpMetier elements
					Element mdpMetier = doc.createElement(MDPMETIER);
					mdpMetier.appendChild(doc.createTextNode(param
							.getMdpMetier()));
					authority.appendChild(mdpMetier);

					// idAppMetier elements
					Element idAppMetier = doc.createElement(IDAPPMETIER);
					idAppMetier.appendChild(doc.createTextNode(param
							.getIdAppMetier()));
					authority.appendChild(idAppMetier);

					// idServMetier elements
					Element idServMetier = doc.createElement(IDSERVMETIER);
					idServMetier.appendChild(doc.createTextNode(param
							.getIdServMetier()));
					authority.appendChild(idServMetier);

					// idOrgMetier elements
					Element idOrgMetier = doc.createElement(IDORGMETIER);
					idOrgMetier.appendChild(doc.createTextNode(param
							.getIdOrgMetier()));
					authority.appendChild(idOrgMetier);

					// certSign elements
					Element certSign = doc.createElement(CERTSIGN);
					certSign.appendChild(doc.createTextNode(param.getCertSign()));
					authority.appendChild(certSign);

					// mdpCert elements
					Element mdpCert = doc.createElement(MDPCERT);
					mdpCert.appendChild(doc.createTextNode(param.getMdpCert()));
					authority.appendChild(mdpCert);

					// certChiff elements
					Element certChiff = doc.createElement(CERTCHIFF);
					certChiff.appendChild(doc.createTextNode(param
							.getCertChiff()));
					authority.appendChild(certChiff);

					Element certDecipher = doc.createElement(CERTDECIPHER);
					certDecipher.appendChild(doc.createTextNode(param
							.getCertDecipher()));
					authority.appendChild(certDecipher);

					// mdpDecipher elements
					Element mdpDecipher = doc.createElement(MDPDECIPHER);
					mdpDecipher.appendChild(doc.createTextNode(param
							.getMdpDecipher()));
					authority.appendChild(mdpDecipher);

					// servPDFCert elements
					Element servPDFCert = doc.createElement(SERVPDFCERT);
					servPDFCert.appendChild(doc.createTextNode(param
							.getServPDFCert()));
					authority.appendChild(servPDFCert);

					// pathPDFCert elements
					Element pathPDFCert = doc.createElement(PATHPDFCERT);
					pathPDFCert.appendChild(doc.createTextNode(param
							.getPathPDFCert()));
					authority.appendChild(pathPDFCert);

					// loginPDFCert elements
					Element loginPDFCert = doc.createElement(LOGINPDFCERT);
					loginPDFCert.appendChild(doc.createTextNode(param
							.getLoginPDFCert()));
					authority.appendChild(loginPDFCert);

					Element mdpPDFCert = doc.createElement(MDPPDFCERT);
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
					Element authority = doc.createElement(CERTAUTHORITY);
					rootElement.appendChild(authority);

					// set attribute to authority element
					Attr attr = doc.createAttribute(TYPE);
					attr.setValue("2");
					authority.setAttributeNode(attr);

					authority.setAttribute(NAME, param.getAuthority());

					// shorten way
					// authority.setAttribute("id", "1");

					// CertPath elements
					Element CertPath = doc.createElement(CERTPATH);
					CertPath.appendChild(doc.createTextNode(param.getCertPath()));
					authority.appendChild(CertPath);

					// TempPath elements
					Element TempPath = doc.createElement(TEMPPATH);
					TempPath.appendChild(doc.createTextNode(param.getTempPath()));
					authority.appendChild(TempPath);

					// SavePath elements
					Element SavePath = doc.createElement(SAVEPATH);
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
			{
				System.out.println("File saved! " + xmlFile.getAbsolutePath());
				created = true;
			}
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

		return created;
	}

	@SuppressWarnings({ "unchecked" })
	public static AuthorityParameters readConfig(String configFile)
	{
		AuthorityParameters autho = null;
		try
		{
			// First create a new XMLInputFactory
			XMLInputFactory inputFactory = XMLInputFactory.newInstance();
			// Setup a new eventReader
			InputStream in = new FileInputStream(configFile);
			XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
			// Read the XML document
			// Item item = null;

			while (eventReader.hasNext())
			{
				XMLEvent event = eventReader.nextEvent();

				if (event.isStartElement())
				{
					StartElement startElement = event.asStartElement();
					// If we have a item element we create a new item
					if ((CERTAUTHORITY).equals(startElement.getName()
							.getLocalPart()))
					{
						// item = new Item();
						// We read the attributes from this tag and add the date
						// attribute to our object
						Iterator<Attribute> attributes = startElement
								.getAttributes();
						while (attributes.hasNext())
						{
							Attribute attribute = attributes.next();
							if ((NAME).equals(attribute.getName().toString()))
							{
								String authority = attribute.getValue();
								if ((KEY).equals(authority))
								{
									autho = new KeynectisParameters();
								}
								else if ((DICTAO) == authority)
								{
									autho = new DictaoParamaters();
								}
							}

						}
					}

					if (event.isStartElement())
					{
						if ((CERTMETIER).equals(event.asStartElement()
								.getName().getLocalPart()))
						{
							event = eventReader.nextEvent();
							((KeynectisParameters) autho).setCertMetier(event
									.asCharacters().getData());
							continue;
						}
					}
					if ((MDPMETIER).equals(event.asStartElement().getName()
							.getLocalPart()))
					{
						event = eventReader.nextEvent();
						((KeynectisParameters) autho).setMdpMetier(event
								.asCharacters().getData());
						continue;
					}
					if ((IDAPPMETIER).equals(event.asStartElement().getName()
							.getLocalPart()))
					{
						event = eventReader.nextEvent();
						((KeynectisParameters) autho).setIdAppMetier(event
								.asCharacters().getData());
						continue;
					}
					if ((IDSERVMETIER).equals(event.asStartElement().getName()
							.getLocalPart()))
					{
						event = eventReader.nextEvent();
						((KeynectisParameters) autho).setIdServMetier(event
								.asCharacters().getData());
						continue;
					}
					if ((IDORGMETIER).equals(event.asStartElement().getName()
							.getLocalPart()))
					{
						event = eventReader.nextEvent();
						((KeynectisParameters) autho).setIdOrgMetier(event
								.asCharacters().getData());
						continue;
					}
					if ((CERTSIGN).equals(event.asStartElement().getName()
							.getLocalPart()))
					{
						event = eventReader.nextEvent();
						((KeynectisParameters) autho).setCertSign(event
								.asCharacters().getData());
						continue;
					}
					if ((MDPCERT).equals(event.asStartElement().getName()
							.getLocalPart()))
					{
						event = eventReader.nextEvent();
						((KeynectisParameters) autho).setMdpCert(event
								.asCharacters().getData());
						continue;
					}
					if ((CERTCHIFF).equals(event.asStartElement().getName()
							.getLocalPart()))
					{
						event = eventReader.nextEvent();
						((KeynectisParameters) autho).setCertChiff(event
								.asCharacters().getData());
						continue;
					}
					if ((CERTDECIPHER).equals(event.asStartElement().getName()
							.getLocalPart()))
					{
						event = eventReader.nextEvent();
						((KeynectisParameters) autho).setCertDecipher(event
								.asCharacters().getData());
						continue;
					}
					if ((MDPDECIPHER).equals(event.asStartElement().getName()
							.getLocalPart()))
					{
						event = eventReader.nextEvent();
						((KeynectisParameters) autho).setMdpDecipher(event
								.asCharacters().getData());
						continue;
					}
					if ((SERVPDFCERT).equals(event.asStartElement().getName()
							.getLocalPart()))
					{
						event = eventReader.nextEvent();
						((KeynectisParameters) autho).setServPDFCert(event
								.asCharacters().getData());
						continue;
					}
					if ((PATHPDFCERT).equals(event.asStartElement().getName()
							.getLocalPart()))
					{
						event = eventReader.nextEvent();
						((KeynectisParameters) autho).setPathPDFCert(event
								.asCharacters().getData());
						continue;
					}
					if ((LOGINPDFCERT).equals(event.asStartElement().getName()
							.getLocalPart()))
					{
						event = eventReader.nextEvent();
						((KeynectisParameters) autho).setLoginPDFCert(event
								.asCharacters().getData());
						continue;
					}
					if ((MDPPDFCERT).equals(event.asStartElement().getName()
							.getLocalPart()))
					{
						event = eventReader.nextEvent();
						((KeynectisParameters) autho).setMdpPDFCert(event
								.asCharacters().getData());
						continue;
					}

					if ((CERTPATH).equals(event.asStartElement().getName()
							.getLocalPart()))
					{
						event = eventReader.nextEvent();
						autho.setCertPath(event.asCharacters().getData());
						continue;
					}
					if ((TEMPPATH).equals(event.asStartElement().getName()
							.getLocalPart()))
					{
						event = eventReader.nextEvent();
						autho.setTempPath(event.asCharacters().getData());
						continue;
					}
					if ((SAVEPATH).equals(event.asStartElement().getName()
							.getLocalPart()))
					{
						event = eventReader.nextEvent();
						autho.setSavePath(event.asCharacters().getData());
						continue;
					}

				}
				// If we reach the end of an item element we add it to the list
				if (event.isEndElement())
				{
					EndElement endElement = event.asEndElement();
					if ((CERTAUTHORITY).equals(endElement.getName()
							.getLocalPart()))
					{

					}
				}

			}
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (XMLStreamException e)
		{
			e.printStackTrace();
		}
		return autho;
	}

}
