package domain;

import java.util.HashSet;
import java.util.Set;

public class DocumentPDF  {

	//nom, Adresse, bool Authent
	private long id;
	private String name;
	private String url;
	private boolean certified;
	
	
	private Set<Signature> signatures = new HashSet<Signature>();
	
	private Utilisateur owner;
	
	public DocumentPDF(){
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public boolean isCertified() {
		return certified;
	}

	public void setCertified(boolean certified) {
		this.certified = certified;
	}

	public Utilisateur getOwner() {
		return owner;
	}

	public void setOwner(Utilisateur owner) {
		this.owner = owner;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Set<Signature> getSignatures() {
		return signatures;
	}

	public void setSignatures(Set<Signature> signatures) {
		this.signatures = signatures;
	}


}
