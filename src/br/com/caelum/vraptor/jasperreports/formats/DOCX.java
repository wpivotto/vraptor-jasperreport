package br.com.caelum.vraptor.jasperreports.formats;

import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporterParameter;
import br.com.caelum.vraptor.jasperreports.ExportFormat;

public class DOCX implements ExportFormat {
	
	private JRExporter exporter;

	public DOCX(){
		this.exporter = new JRDocxExporter();
		configure(JRDocxExporterParameter.FLEXIBLE_ROW_HEIGHT, Boolean.TRUE);
		configure(JRDocxExporterParameter.CHARACTER_ENCODING, "UTF-8");
	}

	public String getContentType() {
		return "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
	}

	public String getExtension() {
		return ".docx";
	}

	public JRExporter getExporter() {
		return exporter;
	}
	
	public void configure(JRExporterParameter parameter, Object value) {
		exporter.setParameter(parameter, value);
	}

	public String toString() {
		return "docx";
	}
	
}
