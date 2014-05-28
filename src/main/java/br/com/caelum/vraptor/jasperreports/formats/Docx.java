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
public class Docx extends AbstractExporter {

	protected SimpleDocxReportConfiguration reportConfiguration = new SimpleDocxReportConfiguration();
	protected SimpleDocxExporterConfiguration exportConfiguration = new SimpleDocxExporterConfiguration();
	
	public String getContentType() {
		return "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
	}

	public String getExtension() {
		return "docx";
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
