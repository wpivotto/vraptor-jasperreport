package br.com.caelum.vraptor.jasperreports.formats;

import javax.enterprise.context.ApplicationScoped;

import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.Exporter;
import net.sf.jasperreports.export.ExporterConfiguration;
import net.sf.jasperreports.export.ReportExportConfiguration;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.export.SimplePdfReportConfiguration;

/**
 * @author William Pivotto
 */

@ApplicationScoped
public class Pdf extends AbstractExporter {
	
	protected SimplePdfReportConfiguration reportConfiguration = new SimplePdfReportConfiguration();
	protected SimplePdfExporterConfiguration exportConfiguration = new SimplePdfExporterConfiguration();

	public String getContentType() {
		return "application/pdf";
	}
	
	public String getExtension() {
		return "pdf";
	}

	@SuppressWarnings("rawtypes")
	public Exporter setup() {
		exportConfiguration.setCompressed(Boolean.TRUE);
		return new JRPdfExporter();
	}

	public ReportExportConfiguration getReportConfiguration() {
		return reportConfiguration;
	}

	public ExporterConfiguration getExporterConfiguration() {
		return exportConfiguration;
	}

}
