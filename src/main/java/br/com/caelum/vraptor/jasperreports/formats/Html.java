package br.com.caelum.vraptor.jasperreports.formats;

import java.io.OutputStream;

import javax.enterprise.context.ApplicationScoped;

import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.export.Exporter;
import net.sf.jasperreports.export.ExporterConfiguration;
import net.sf.jasperreports.export.ExporterOutput;
import net.sf.jasperreports.export.ReportExportConfiguration;
import net.sf.jasperreports.export.SimpleHtmlExporterConfiguration;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;
import net.sf.jasperreports.export.SimpleHtmlReportConfiguration;

/**
 * @author William Pivotto
 */

@ApplicationScoped
public class Html extends AbstractExporter {
	
	protected SimpleHtmlReportConfiguration reportConfiguration = new SimpleHtmlReportConfiguration();
	protected SimpleHtmlExporterConfiguration exportConfiguration = new SimpleHtmlExporterConfiguration();

	public String getContentType() {
		return "text/html";
	}

	public String getExtension() {
		return "html";
	}

	@SuppressWarnings("rawtypes")
	public Exporter setup() {
		return new HtmlExporter();
	}

	public ReportExportConfiguration getReportConfiguration() {
		return reportConfiguration;
	}

	public ExporterConfiguration getExporterConfiguration() {
		return exportConfiguration;
	}
	
	public ExporterOutput getExporterOutput(OutputStream output) {
		return new SimpleHtmlExporterOutput(output, "UTF-8");
	}
	
}
