package br.com.caelum.vraptor.jasperreports.formats;

import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;
import br.com.caelum.vraptor.jasperreports.ExportFormat;

/**
 * @author William Pivotto
 */

public class PDF implements ExportFormat {
	
	private JRExporter exporter;

	public PDF(){
		this.exporter = new JRPdfExporter();
		configure(JRPdfExporterParameter.IS_COMPRESSED, Boolean.TRUE );
		configure(JRPdfExporterParameter.CHARACTER_ENCODING, "UTF-8" );
	}

	public String getContentType() {
		return "application/pdf";
	}

	public JRExporter getExporter() {
		return exporter;
	}

	public String getExtension() {
		return ".pdf";
	}

	public String toString() {
		return "pdf";
	}
	
	public void configure(JRExporterParameter parameter, Object value) {
		exporter.setParameter(parameter, value);
	}

}
