package domain;

public class Signature {

	private long id;
	private String name;
	
	private float signatureX;
	private float signatureY;
	private float heightSignature;
	private float widthSignature;
	
	private int pageNumber;
	private DocumentPDF document;
	
	public Signature(){
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public DocumentPDF getDocument() {
		return document;
	}

	public void setDocument(DocumentPDF document) {
		this.document = document;
	}
	
	
	
}
