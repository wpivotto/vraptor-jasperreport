package br.com.caelum.vraptor.jasperreports.formats;

import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.jasperreports.ExportFormat;

/**
 * @author William Pivotto
 */

@Component
public class Html implements ExportFormat {
	
	private JRExporter exporter;

	public Html(){
		this.exporter = new JRHtmlExporter();
	}

	public String getContentType() {
		return "text/html";
	}

	public String getExtension() {
		return "html";
	}

	public JRExporter getExporter() {
		return exporter;
	}
	
	public ExportFormat configure(JRExporterParameter parameter, Object value) {
		exporter.setParameter(parameter, value);
		return this;
	}
}
