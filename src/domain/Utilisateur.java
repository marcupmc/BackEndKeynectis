package domain; 

import java.sql.Blob;
import java.util.HashSet;
import java.util.Set;

public class Utilisateur
{

	private long id;
	private String identifiant;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String phoneNumber; // A changer en une table telephone?
	private Set<DocumentPDF> documents = new HashSet<DocumentPDF>();
	private Blob signature;

	public Utilisateur()
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

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getPhoneNumber()
	{
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber)
	{
		this.phoneNumber = phoneNumber;
	}

	public Set<DocumentPDF> getDocuments()
	{
		return documents;
	}

	public void setDocuments(Set<DocumentPDF> documents)
	{
		this.documents = documents;
	}

	public String getIdentifiant()
	{
		return identifiant;
	}

	public void setIdentifiant(String identifiant)
	{
		this.identifiant = identifiant;
	}

	public Blob getSignature()
	{
		return signature;
	}

	public void setSignature(Blob signature)
	{
		this.signature = signature;
	}

}
