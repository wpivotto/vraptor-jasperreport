package br.com.caelum.vraptor.jasperreports.formats;

import java.io.File;
import java.io.OutputStream;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.export.Exporter;
import net.sf.jasperreports.export.ExporterConfiguration;
import net.sf.jasperreports.export.ExporterOutput;
import net.sf.jasperreports.export.ReportExportConfiguration;
import net.sf.jasperreports.export.SimpleHtmlExporterConfiguration;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;
import net.sf.jasperreports.export.SimpleHtmlReportConfiguration;
import net.sf.jasperreports.web.util.WebHtmlResourceHandler;
import br.com.caelum.vraptor.jasperreports.ReportPathResolver;

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
		SimpleHtmlExporterOutput exporterOutput = new SimpleHtmlExporterOutput(output, "UTF-8");
		exporterOutput.setImageHandler(new WebHtmlResourceHandler("servlets/image?image={0}"));
		return exporterOutput;
	}
	
}
