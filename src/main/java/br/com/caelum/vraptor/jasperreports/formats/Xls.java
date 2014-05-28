package br.com.caelum.vraptor.jasperreports.formats;

import javax.enterprise.context.ApplicationScoped;

import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.export.Exporter;
import net.sf.jasperreports.export.ExporterConfiguration;
import net.sf.jasperreports.export.ReportExportConfiguration;
import net.sf.jasperreports.export.SimpleXlsExporterConfiguration;
import net.sf.jasperreports.export.SimpleXlsReportConfiguration;

/**
 * @author William Pivotto
 */

@ApplicationScoped
public class Xls extends AbstractExporter {
	
	protected SimpleXlsReportConfiguration reportConfiguration = new SimpleXlsReportConfiguration();
	protected SimpleXlsExporterConfiguration exportConfiguration = new SimpleXlsExporterConfiguration();
	
	public String getContentType() {
		return "application/vnd.ms-excel";
	}

	public String getExtension() {
		return "xls";
	}

	@SuppressWarnings("rawtypes")
	public Exporter setup() {
		return new JRXlsExporter();  
	}

	public ReportExportConfiguration getReportConfiguration() {
		reportConfiguration.setOnePagePerSheet(true);
		return reportConfiguration;
	}

	public ExporterConfiguration getExporterConfiguration() {
		return exportConfiguration;
	}

}
