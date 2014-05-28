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
		return new SimpleHtmlReportConfiguration();
	}

	public ExporterConfiguration getExporterConfiguration() {
		return new SimpleHtmlExporterConfiguration();
	}
	
	public ExporterOutput getExporterOutput(OutputStream output) {
		return new SimpleHtmlExporterOutput(output, "UTF-8");
	}
	
}
