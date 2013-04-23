package domain;

public class DocumentPDF  {

	//nom, Adresse, bool Authent
	private long id;
	private String name;
	private String url;
	private boolean certified;
	private String signatureName;
	
	private float signatureX;
	private float signatureY;
	private float heightSignature;
	private float widthSignature;
	
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

	public String getSignatureName() {
		return signatureName;
	}

	public void setSignatureName(String signatureName) {
		this.signatureName = signatureName;
	}

	public float getSignatureX() {
		return signatureX;
	}

	public void setSignatureX(float signatureX) {
		this.signatureX = signatureX;
	}

	public float getSignatureY() {
		return signatureY;
	}

	public void setSignatureY(float signatureY) {
		this.signatureY = signatureY;
	}

	public float getHeightSignature() {
		return heightSignature;
	}

	public void setHeightSignature(float heightSignature) {
		this.heightSignature = heightSignature;
	}

	public float getWidthSignature() {
		return widthSignature;
	}

	public void setWidthSignature(float widthSignature) {
		this.widthSignature = widthSignature;
	}


}
