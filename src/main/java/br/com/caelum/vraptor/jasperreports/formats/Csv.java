package br.com.caelum.vraptor.jasperreports.formats;

import javax.enterprise.context.RequestScoped;

import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.export.JRCsvExporter;

/**
 * @author William Pivotto
 */

@RequestScoped
public class Csv extends AbstractExporter {

	public String getContentType() {
		return "application/csv";
	}

	public String getExtension() {
		return "csv";
	}

	public JRExporter setup() {
		return new JRCsvExporter();
	}
	
	
}
