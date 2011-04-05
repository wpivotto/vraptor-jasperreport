package br.com.caelum.vraptor.jasperreports.formats;

import br.com.caelum.vraptor.jasperreports.ExportFormat;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.export.JRHtmlExporter;

public class HTML implements ExportFormat {
	
	private JRExporter exporter;

	public HTML(){
		this.exporter = new JRHtmlExporter();
	}

	public String getContentType() {
		return "html";
	}

	public String getExtension() {
		return ".html";
	}

	public JRExporter getExporter() {
		return exporter;
	}
	
	public void configure(JRExporterParameter parameter, Object value) {
		exporter.setParameter(parameter, value);
	}
	
	public String toString() {
		return "html";
	}

}
