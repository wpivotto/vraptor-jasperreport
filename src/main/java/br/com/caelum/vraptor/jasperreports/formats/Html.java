package br.com.caelum.vraptor.jasperreports.formats;

import net.sf.jasperreports.engine.export.JRHtmlExporter;
import br.com.caelum.vraptor.ioc.Component;

/**
 * @author William Pivotto
 */

@Component
public class Html extends AbstractExporter {

	public Html(){
		
	}

	public String getContentType() {
		return "text/html";
	}

	public String getExtension() {
		return "html";
	}

	public void setup() {
		exporter = new JRHtmlExporter();
	}
	
}
