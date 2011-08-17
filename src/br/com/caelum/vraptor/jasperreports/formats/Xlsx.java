package br.com.caelum.vraptor.jasperreports.formats;

import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.jasperreports.ExportFormat;

/**
 * @author William Pivotto
 */

@Component
public class Xlsx implements ExportFormat {
	
	private JRExporter exporter;

	public Xlsx(){
		this.exporter = new JRXlsxExporter();  
		configure(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.TRUE);
		configure(JRXlsExporterParameter.CHARACTER_ENCODING, "UTF-8");
	}

	public String getContentType() {
		return "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	}

	public String getExtension() {
		return "xlsx";
	}

	public JRExporter getExporter() {
		return exporter;
	}
	
	public ExportFormat configure(JRExporterParameter parameter, Object value) {
		exporter.setParameter(parameter, value);
		return this;
	}

}
