package br.com.caelum.vraptor.jasperreports.formats;

import javax.enterprise.context.RequestScoped;

import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.export.oasis.JROdsExporter;

/**
 * @author William Pivotto
 */

@RequestScoped
public class Ods extends AbstractExporter {
	
	public String getContentType() {
		return "application/vnd.oasis.opendocument.spreadsheet";
	}

	public String getExtension() {
		return "ods";
	}
	
	public JRExporter setup() {
		return new JROdsExporter();
	}

}
