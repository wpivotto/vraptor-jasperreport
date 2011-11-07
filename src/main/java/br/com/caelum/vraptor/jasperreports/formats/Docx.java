package br.com.caelum.vraptor.jasperreports.formats;

import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporterParameter;
import br.com.caelum.vraptor.ioc.Component;

/**
 * @author William Pivotto
 */

@Component
public class Docx extends AbstractExporter {

	public String getContentType() {
		return "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
	}

	public String getExtension() {
		return "docx";
	}

	public JRExporter setup() {
		configure(JRDocxExporterParameter.FLEXIBLE_ROW_HEIGHT, Boolean.TRUE);
		return new JRDocxExporter();
	}
	
}
