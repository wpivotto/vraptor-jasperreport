package br.com.caelum.vraptor.jasperreports.formats;

import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.export.ooxml.JRPptxExporter;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.jasperreports.ExportFormat;

/**
 * @author William Pivotto
 */

@Component
public class Pptx implements ExportFormat {
	
	private JRExporter exporter;

	public Pptx(){
		this.exporter = new JRPptxExporter();
	}

	public String getContentType() {
		return "application/vnd.openxmlformats-officedocument.presentationml.presentation";
	}

	public JRExporter getExporter() {
		return exporter;
	}

	public String getExtension() {
		return "pptx";
	}
	
	public ExportFormat configure(JRExporterParameter parameter, Object value) {
		exporter.setParameter(parameter, value);
		return this;
	}

}
