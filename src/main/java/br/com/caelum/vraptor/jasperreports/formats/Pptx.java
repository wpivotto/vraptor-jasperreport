package br.com.caelum.vraptor.jasperreports.formats;

import javax.enterprise.context.ApplicationScoped;

import net.sf.jasperreports.engine.export.ooxml.JRPptxExporter;
import net.sf.jasperreports.export.Exporter;
import net.sf.jasperreports.export.ExporterConfiguration;
import net.sf.jasperreports.export.ReportExportConfiguration;
import net.sf.jasperreports.export.SimplePptxExporterConfiguration;
import net.sf.jasperreports.export.SimplePptxReportConfiguration;

/**
 * @author William Pivotto
 */

@ApplicationScoped
public class Pptx extends AbstractExporter {
	
	protected SimplePptxReportConfiguration reportConfiguration = new SimplePptxReportConfiguration();
	protected SimplePptxExporterConfiguration exportConfiguration = new SimplePptxExporterConfiguration();
	
	public String getContentType() {
		return "application/vnd.openxmlformats-officedocument.presentationml.presentation";
	}
	
	public String getExtension() {
		return "pptx";
	}

	@SuppressWarnings("rawtypes")
	public Exporter setup() {
		return new JRPptxExporter();
	}

	public ReportExportConfiguration getReportConfiguration() {
		return reportConfiguration;
	}

	public ExporterConfiguration getExporterConfiguration() {
		return exportConfiguration;
	}

}
