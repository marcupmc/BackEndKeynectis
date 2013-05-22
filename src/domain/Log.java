package domain;

import java.util.Date;

public class Log
{

	private long id;
	private TypeLog type;
	private Date date;
	private String ipadresse;
	private String identifiant_client;
	private EventType eventype;

	public Log()
	{

	}

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public TypeLog getType()
	{
		return type;
	}

	public void setType(TypeLog type)
	{
		this.type = type;
	}

	public Date getDate()
	{
		return date;
	}

	public void setDate(Date date)
	{
		this.date = date;
	}

	public String getIpadresse()
	{
		return ipadresse;
	}

	public void setIpadresse(String ipadresse)
	{
		this.ipadresse = ipadresse;
	}

	public String getIdentifiant_client()
	{
		return identifiant_client;
	}

	public void setIdentifiant_client(String identifiant_client)
	{
		this.identifiant_client = identifiant_client;
	}

	public EventType getEventype()
	{
		return eventype;
	}

	public void setEventype(EventType eventype)
	{
		this.eventype = eventype;

	}
}
