package br.com.caelum.vraptor.jasperreports.formats;

import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import br.com.caelum.vraptor.ioc.Component;

/**
 * @author William Pivotto
 */

@Component
public class Xls extends AbstractExporter {
	
	public String getContentType() {
		return "application/vnd.ms-excel";
	}

	public String getExtension() {
		return "xls";
	}

	public void setup() {
		exporter = new JRXlsExporter();  
		configure(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.TRUE);
		configure(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);   
		configure(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS, Boolean.TRUE);  
		configure(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.FALSE);  
	}

}
