package br.com.caelum.vraptor.jasperreports.formats;

import javax.enterprise.context.RequestScoped;

import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;

/**
 * @author William Pivotto
 */

@RequestScoped
public class Xlsx extends AbstractExporter {

	public String getContentType() {
		return "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	}

	public String getExtension() {
		return "xlsx";
	}

	public JRExporter setup() {
		configure(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.TRUE);
		return new JRXlsxExporter();  
	}

}
