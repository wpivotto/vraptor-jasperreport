package br.com.caelum.vraptor.jasperreports.formats;

import net.sf.jasperreports.engine.export.JRXhtmlExporter;
import br.com.caelum.vraptor.ioc.Component;

/**
 * @author William Pivotto
 */

@Component
public class Xhtml extends AbstractExporter {

	public String getContentType() {
		return "text/html";
	}

	public String getExtension() {
		return "xhtml";
	}

	public void setup() {
		exporter = new JRXhtmlExporter();
	}
	
}
