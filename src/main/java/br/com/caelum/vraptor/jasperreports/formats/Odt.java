package br.com.caelum.vraptor.jasperreports.formats;

import javax.enterprise.context.RequestScoped;

import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.export.oasis.JROdtExporter;

/**
 * @author William Pivotto
 */

@RequestScoped
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
