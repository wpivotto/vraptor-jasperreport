package br.com.caelum.vraptor.jasperreports.formats;

import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.export.JRTextExporter;
import net.sf.jasperreports.engine.export.JRTextExporterParameter;
import br.com.caelum.vraptor.jasperreports.ExportFormat;

public class RTF implements ExportFormat {
	
	private JRExporter exporter;

	public RTF(){
		this.exporter = new JRTextExporter();
		configure(JRTextExporterParameter.CHARACTER_ENCODING, "UTF-8");
		configure(JRTextExporterParameter.CHARACTER_WIDTH, 5f);
		configure(JRTextExporterParameter.CHARACTER_HEIGHT, 20f);
	}

	public JRExporter getExporter() {
		return exporter;
	}

	public String getContentType() {
		return "application/rtf";
	}

	public String getExtension() {
		return ".rtf";
	}

	public String toString() {
		return "rtf";
	}

	public void configure(JRExporterParameter parameter, Object value) {
		exporter.setParameter(parameter, value);
	}

}
