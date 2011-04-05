package br.com.caelum.vraptor.jasperreports.formats;

import br.com.caelum.vraptor.jasperreports.ExportFormat;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.export.JRTextExporter;
import net.sf.jasperreports.engine.export.JRTextExporterParameter;

public class TXT implements ExportFormat {
	
	private JRExporter exporter;

	public TXT(){
		this.exporter = new JRTextExporter();
	}

	public String getContentType() {
		return "text/plain";
	}

	public JRExporter getExporter() {
		configure(JRTextExporterParameter.CHARACTER_ENCODING, "UTF-8");
		configure(JRTextExporterParameter.CHARACTER_WIDTH, 5f);
		configure(JRTextExporterParameter.CHARACTER_HEIGHT, 20f);
		return exporter;
	}

	public String getExtension() {
		return ".txt";
	}
	
	public void configure(JRExporterParameter parameter, Object value) {
		exporter.setParameter(parameter, value);
	}


}
