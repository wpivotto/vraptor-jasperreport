package br.com.caelum.vraptor.jasperreports.formats;

import javax.enterprise.context.ApplicationScoped;

import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.export.Exporter;
import net.sf.jasperreports.export.ExporterConfiguration;
import net.sf.jasperreports.export.ReportExportConfiguration;
import net.sf.jasperreports.export.SimpleDocxExporterConfiguration;
import net.sf.jasperreports.export.SimpleDocxReportConfiguration;

/**
 * @author William Pivotto
 */

@ApplicationScoped
public class Doc extends AbstractExporter {

	protected SimpleDocxReportConfiguration reportConfiguration = new SimpleDocxReportConfiguration();
	protected SimpleDocxExporterConfiguration exportConfiguration = new SimpleDocxExporterConfiguration();
	
	public String getContentType() {
		return "application/msword";
	}

	public String getExtension() {
		return "doc";
	}

	@SuppressWarnings("rawtypes")
	public Exporter setup() {
		return new JRDocxExporter();
	}
	
	public ReportExportConfiguration getReportConfiguration() {
		return reportConfiguration;
	}

	public ExporterConfiguration getExporterConfiguration() {
		return exportConfiguration;
	}
	
}
