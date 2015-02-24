package br.com.caelum.vraptor.jasperreports.serializer;

public class SerializedReport {
	
	private final String src;
	private final String type;
	
	public SerializedReport(String src, String type) {
		this.src = src;
		this.type = type;
	}

	public String getSrc() {
		return src;
	}

	public String getType() {
		return type;
	}

}
