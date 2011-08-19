package br.com.caelum.vraptor.jasperreports.formats;

import net.sf.jasperreports.engine.export.ooxml.JRPptxExporter;
import br.com.caelum.vraptor.ioc.Component;

/**
 * @author William Pivotto
 */

@Component
public class Pptx extends AbstractExporter {
	
	public String getContentType() {
		return "application/vnd.openxmlformats-officedocument.presentationml.presentation";
	}
	
	public String getExtension() {
		return "pptx";
	}

	public void setup() {
		exporter = new JRPptxExporter();
	}

}
