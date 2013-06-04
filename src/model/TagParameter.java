/**
 * 
 */
package model;

import domain.CertificationType;

/**
 * @author dtadmi
 * 
 */
public class TagParameter
{

	private CertificationType type = null;

	private String name = "";
	private String id_type = "";
	private String DATA_METIER = "DATA_METIER=contrat\n";
	private String PDF_REASON = "";
	private String PDF_LOCATION = "";
	private String PDF_CONTACT = "";
	private boolean defaut = false;

	/**
	 * 
	 */
	public TagParameter()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param name
	 * @param id_type
	 */
	public TagParameter(String name, String id_type)
	{
		super();
		this.name = name;
		this.id_type = id_type;
	}

	/**
	 * @param name
	 * @param id_type
	 * @param pDF_REASON
	 * @param pDF_LOCATION
	 * @param pDF_CONTACT
	 */
	public TagParameter(String name, String id_type, String pDF_REASON,
			String pDF_LOCATION, String pDF_CONTACT)
	{
		super();
		this.name = name;
		this.id_type = id_type;
		PDF_REASON = pDF_REASON;
		PDF_LOCATION = pDF_LOCATION;
		PDF_CONTACT = pDF_CONTACT;
	}

	/**
	 * @param name
	 * @param id_type
	 * @param dATA_METIER
	 * @param pDF_REASON
	 * @param pDF_LOCATION
	 * @param pDF_CONTACT
	 */
	public TagParameter(String name, String id_type, String dATA_METIER,
			String pDF_REASON, String pDF_LOCATION, String pDF_CONTACT)
	{
		super();
		this.name = name;
		this.id_type = id_type;
		DATA_METIER = dATA_METIER;
		PDF_REASON = pDF_REASON;
		PDF_LOCATION = pDF_LOCATION;
		PDF_CONTACT = pDF_CONTACT;
	}

	/**
	 * @param name
	 * @param id_type
	 * @param pDF_REASON
	 * @param pDF_LOCATION
	 * @param pDF_CONTACT
	 * @param defaut
	 */
	public TagParameter(String name, String id_type, String pDF_REASON,
			String pDF_LOCATION, String pDF_CONTACT, boolean defaut)
	{
		super();
		this.name = name;
		this.id_type = id_type;
		PDF_REASON = pDF_REASON;
		PDF_LOCATION = pDF_LOCATION;
		PDF_CONTACT = pDF_CONTACT;
		this.defaut = defaut;
	}

	/**
	 * @param name
	 * @param id_type
	 * @param dATA_METIER
	 * @param pDF_REASON
	 * @param pDF_LOCATION
	 * @param pDF_CONTACT
	 * @param defaut
	 */
	public TagParameter(String name, String id_type, String dATA_METIER,
			String pDF_REASON, String pDF_LOCATION, String pDF_CONTACT,
			boolean defaut)
	{
		super();
		this.name = name;
		this.id_type = id_type;
		DATA_METIER = dATA_METIER;
		PDF_REASON = pDF_REASON;
		PDF_LOCATION = pDF_LOCATION;
		PDF_CONTACT = pDF_CONTACT;
		this.defaut = defaut;
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
	 * @return the type
	 */
	public CertificationType getType()
	{
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(CertificationType type)
	{
		this.type = type;
	}

	/**
	 * @param defaut
	 *            the defaut to set
	 */
	public void setDefaut(boolean defaut)
	{
		this.defaut = defaut;
	}

	public void modifyParameters(String name, String id_type,
			String pDF_REASON, String pDF_LOCATION, String pDF_CONTACT,
			boolean defaut)
	{
		this.name = name;
		this.id_type = id_type;
		PDF_REASON = pDF_REASON;
		PDF_LOCATION = pDF_LOCATION;
		PDF_CONTACT = pDF_CONTACT;
		this.defaut = defaut;

	}

	public boolean equals(TagParameter type)
	{
		return ((name.equals(type.getName())) && (id_type.equals(type
				.getId_type())
				&& (PDF_REASON.equals(type.getPDF_REASON()))
				&& (PDF_LOCATION.equals(type.getPDF_LOCATION())) && (PDF_CONTACT
					.equals(type.getPDF_CONTACT()))));
	}

}
