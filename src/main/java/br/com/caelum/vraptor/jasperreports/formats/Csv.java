package br.com.caelum.vraptor.jasperreports.formats;

import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import br.com.caelum.vraptor.ioc.Component;

/**
 * @author William Pivotto
 */

@Component
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
