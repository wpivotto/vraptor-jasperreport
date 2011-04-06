package br.com.caelum.vraptor.jasperreports.formats;

import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.export.oasis.JROdtExporter;
import br.com.caelum.vraptor.jasperreports.ExportFormat;

public class ODT implements ExportFormat {
	
	private JRExporter exporter;

	public ODT(){
		this.exporter = new JROdtExporter();
	}

	public String getContentType() {
		return "application/vnd.oasis.opendocument.text";
	}

	public JRExporter getExporter() {
		return exporter;
	}

	public String getExtension() {
		return ".odt";
	}
	
	public void configure(JRExporterParameter parameter, Object value) {
		exporter.setParameter(parameter, value);
	}

}
