package br.com.caelum.vraptor.jasperreports.formats;

import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.export.JRXhtmlExporter;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.jasperreports.ExportFormat;

/**
 * @author William Pivotto
 */

@Component
public class Xhtml implements ExportFormat {
	
	private JRExporter exporter;

	public Xhtml(){
		this.exporter = new JRXhtmlExporter();
	}

	public String getContentType() {
		return "text/html";
	}

	public String getExtension() {
		return "xhtml";
	}

	public JRExporter getExporter() {
		return exporter;
	}
	
	public ExportFormat configure(JRExporterParameter parameter, Object value) {
		exporter.setParameter(parameter, value);
		return this;
	}
}
