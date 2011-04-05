package br.com.caelum.vraptor.jasperreports.formats;

import br.com.caelum.vraptor.jasperreports.ExportFormat;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;

public class CSV implements ExportFormat {
	
	private JRExporter exporter;

	public CSV(){
		this.exporter = new JRXlsExporter();
	}

	public String getContentType() {
		return "application/csv";
	}

	public String getExtension() {
		return ".csv";
	}

	public JRExporter getExporter() {
		configure(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.TRUE);
		configure(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);   
		configure(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS, Boolean.TRUE);  
		configure(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.FALSE);  
		configure(JRXlsExporterParameter.CHARACTER_ENCODING, "UTF-8");
		return exporter;
	}
	
	public void configure(JRExporterParameter parameter, Object value) {
		exporter.setParameter(parameter, value);
	}
	
	public String toString() {
		return "csv";
	}

}
