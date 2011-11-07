package br.com.caelum.vraptor.jasperreports.formats;

import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.export.oasis.JROdsExporter;
import br.com.caelum.vraptor.ioc.Component;

/**
 * @author William Pivotto
 */

@Component
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
