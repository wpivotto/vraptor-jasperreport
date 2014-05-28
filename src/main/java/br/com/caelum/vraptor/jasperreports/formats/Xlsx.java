package br.com.caelum.vraptor.jasperreports.formats;

import javax.enterprise.context.ApplicationScoped;

import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.Exporter;
import net.sf.jasperreports.export.ExporterConfiguration;
import net.sf.jasperreports.export.ReportExportConfiguration;
import net.sf.jasperreports.export.SimpleXlsxExporterConfiguration;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;

/**
 * @author William Pivotto
 */

@ApplicationScoped
public class Xlsx extends AbstractExporter {
	
	protected SimpleXlsxReportConfiguration reportConfiguration = new SimpleXlsxReportConfiguration();
	protected SimpleXlsxExporterConfiguration exportConfiguration = new SimpleXlsxExporterConfiguration();

	public String getContentType() {
		return "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	}

	public String getExtension() {
		return "xlsx";
	}

	@SuppressWarnings("rawtypes")
	public Exporter setup() {
		return new JRXlsxExporter();  
	}

	public ReportExportConfiguration getReportConfiguration() {
		reportConfiguration.setOnePagePerSheet(true);
		return reportConfiguration;
	}

	public ExporterConfiguration getExporterConfiguration() {
		return exportConfiguration;
	}

}
