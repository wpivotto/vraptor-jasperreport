package br.com.caelum.vraptor.jasperreports.formats;

import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.export.JRTextExporter;
import net.sf.jasperreports.engine.export.JRTextExporterParameter;
import br.com.caelum.vraptor.jasperreports.ExportFormat;

/**
 * @author William Pivotto
 */

public class Txt implements ExportFormat {
	
	private JRExporter exporter;

	public Txt(){
		this.exporter = new JRTextExporter();
		configure(JRTextExporterParameter.CHARACTER_ENCODING, "UTF-8");
		configure(JRTextExporterParameter.CHARACTER_WIDTH, 5f);
		configure(JRTextExporterParameter.CHARACTER_HEIGHT, 20f);
	}

	public String getContentType() {
		return "text/plain";
	}

	public JRExporter getExporter() {
		return exporter;
	}

	public String getExtension() {
		return "txt";
	}
	
	public ExportFormat configure(JRExporterParameter parameter, Object value) {
		exporter.setParameter(parameter, value);
		return this;
	}


}
