package br.com.caelum.vraptor.jasperreports.formats;

import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.export.oasis.JROdsExporter;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.jasperreports.ExportFormat;

/**
 * @author William Pivotto
 */

@Component
public class Ods implements ExportFormat {
	
	private JRExporter exporter;

	public Ods(){
		this.exporter = new JROdsExporter();
	}

	public String getContentType() {
		return "application/vnd.oasis.opendocument.spreadsheet";
	}

	public JRExporter getExporter() {
		return exporter;
	}

	public String getExtension() {
		return "ods";
	}
	
	public ExportFormat configure(JRExporterParameter parameter, Object value) {
		exporter.setParameter(parameter, value);
		return this;
	}

}
