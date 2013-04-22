package domain;

public class DocumentPDF  {

	//nom, Adresse, bool Authent
	private long id;
	private String name;
	private String url;
	private boolean certified;
	private String signatureName;
	
	private int signatureX;
	private int signatureY;
	private int heightSignature;
	private int widthSignature;
	
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

	public int getSignatureX() {
		return signatureX;
	}

	public void setSignatureX(int signatureX) {
		this.signatureX = signatureX;
	}

	public int getSignatureY() {
		return signatureY;
	}

	public void setSignatureY(int signatureY) {
		this.signatureY = signatureY;
	}

	public int getHeightSignature() {
		return heightSignature;
	}

	public void setHeightSignature(int heightSignature) {
		this.heightSignature = heightSignature;
	}

	public int getWidthSignature() {
		return widthSignature;
	}

	public void setWidthSignature(int widthSignature) {
		this.widthSignature = widthSignature;
	}
}
