package br.com.caelum.vraptor.jasperreports.formats;

import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.export.oasis.JROdtExporter;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.jasperreports.ExportFormat;

/**
 * @author William Pivotto
 */

@Component
public class Odt implements ExportFormat {
	
	private JRExporter exporter;

	public Odt(){
		this.exporter = new JROdtExporter();
	}

	public String getContentType() {
		return "application/vnd.oasis.opendocument.text";
	}

	public JRExporter getExporter() {
		return exporter;
	}

	public String getExtension() {
		return "odt";
	}
	
	public ExportFormat configure(JRExporterParameter parameter, Object value) {
		exporter.setParameter(parameter, value);
		return this;
	}

}
