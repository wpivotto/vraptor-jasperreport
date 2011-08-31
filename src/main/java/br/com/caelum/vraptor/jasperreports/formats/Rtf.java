package br.com.caelum.vraptor.jasperreports.formats;

import net.sf.jasperreports.engine.export.JRTextExporter;
import net.sf.jasperreports.engine.export.JRTextExporterParameter;
import br.com.caelum.vraptor.ioc.Component;

/**
 * @author William Pivotto
 */

@Component
public class Rtf extends AbstractExporter {

	public void setup() {
		exporter = new JRTextExporter();
		configure(JRTextExporterParameter.CHARACTER_WIDTH, 5f);
		configure(JRTextExporterParameter.CHARACTER_HEIGHT, 20f);
	}

	public String getContentType() {
		return "application/rtf";
	}

	public String getExtension() {
		return "rtf";
	}

}
