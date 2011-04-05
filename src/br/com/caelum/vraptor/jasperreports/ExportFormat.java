package br.com.caelum.vraptor.jasperreports;

import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;

public interface ExportFormat {
	
	String getContentType();
	
	String getExtension();
	
	JRExporter getExporter();
	
	void configure(JRExporterParameter parameter, Object value);

}
