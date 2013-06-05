/**
 * 
 */
package domain;

import java.util.HashSet;
import java.util.Set;

import model.TagParameter;

/**
 * @author dtadmi
 * 
 */
public class CertificationType
{

	private long id;

	// private TagParameter type = null;
	private Set<DocumentPDF> documents = new HashSet<DocumentPDF>();

	private String name;
	private String id_type;
	private String DATA_METIER;
	private String PDF_REASON;
	private String PDF_LOCATION;
	private String PDF_CONTACT;
	private boolean defaut;

	/**
	 * 
	 */
	public CertificationType()
	{
		
	}

	

	/**
	 * @return the id
	 */
	public long getId()
	{
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(long id)
	{
		this.id = id;
	}

	/**
	 * @return the documents
	 */
	public Set<DocumentPDF> getDocuments()
	{
		return documents;
	}

	/**
	 * @param documents
	 *            the documents to set
	 */
	public void setDocuments(Set<DocumentPDF> documents)
	{
		this.documents = documents;
	}

	/**
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * @return the id_type
	 */
	public String getId_type()
	{
		return id_type;
	}

	/**
	 * @param id_type
	 *            the id_type to set
	 */
	public void setId_type(String id_type)
	{
		this.id_type = id_type;
	}

	/**
	 * @return the dATA_METIER
	 */
	public String getDATA_METIER()
	{
		return DATA_METIER;
	}

	/**
	 * @param dATA_METIER
	 *            the dATA_METIER to set
	 */
	public void setDATA_METIER(String dATA_METIER)
	{
		DATA_METIER = dATA_METIER;
	}

	/**
	 * @return the pDF_REASON
	 */
	public String getPDF_REASON()
	{
		return PDF_REASON;
	}

	/**
	 * @param pDF_REASON
	 *            the pDF_REASON to set
	 */
	public void setPDF_REASON(String pDF_REASON)
	{
		PDF_REASON = pDF_REASON;
	}

	/**
	 * @return the pDF_LOCATION
	 */
	public String getPDF_LOCATION()
	{
		return PDF_LOCATION;
	}

	/**
	 * @param pDF_LOCATION
	 *            the pDF_LOCATION to set
	 */
	public void setPDF_LOCATION(String pDF_LOCATION)
	{
		PDF_LOCATION = pDF_LOCATION;
	}

	/**
	 * @return the pDF_CONTACT
	 */
	public String getPDF_CONTACT()
	{
		return PDF_CONTACT;
	}

	/**
	 * @param pDF_CONTACT
	 *            the pDF_CONTACT to set
	 */
	public void setPDF_CONTACT(String pDF_CONTACT)
	{
		PDF_CONTACT = pDF_CONTACT;
	}

	/**
	 * @return the defaut
	 */
	public boolean isDefaut()
	{
		return defaut;
	}

	/**
	 * @param defaut
	 *            the defaut to set
	 */
	public void setDefaut(boolean defaut)
	{
		this.defaut = defaut;
	}

	

}
