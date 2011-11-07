package br.com.caelum.vraptor.jasperreports.formats;

import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.export.oasis.JROdtExporter;
import br.com.caelum.vraptor.ioc.Component;

/**
 * @author William Pivotto
 */

@Component
public class Odt extends AbstractExporter {

	public String getContentType() {
		return "application/vnd.oasis.opendocument.text";
	}
	
	public String getExtension() {
		return "odt";
	}

	public JRExporter setup() {
		return new JROdtExporter();
	}

}
