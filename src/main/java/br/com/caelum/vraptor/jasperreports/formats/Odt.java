package br.com.caelum.vraptor.jasperreports.formats;

import javax.enterprise.context.ApplicationScoped;

import net.sf.jasperreports.engine.export.oasis.JROdtExporter;
import net.sf.jasperreports.export.Exporter;
import net.sf.jasperreports.export.ExporterConfiguration;
import net.sf.jasperreports.export.ReportExportConfiguration;
import net.sf.jasperreports.export.SimpleOdtExporterConfiguration;
import net.sf.jasperreports.export.SimpleOdtReportConfiguration;

/**
 * @author William Pivotto
 */

@ApplicationScoped
public class Odt extends AbstractExporter {
	
	protected SimpleOdtReportConfiguration reportConfiguration = new SimpleOdtReportConfiguration();
	protected SimpleOdtExporterConfiguration exportConfiguration = new SimpleOdtExporterConfiguration();

	public String getContentType() {
		return "application/vnd.oasis.opendocument.text";
	}
	
	public String getExtension() {
		return "odt";
	}

	@SuppressWarnings("rawtypes")
	public Exporter setup() {
		return new JROdtExporter();
	}

	public ReportExportConfiguration getReportConfiguration() {
		return reportConfiguration;
	}

	public ExporterConfiguration getExporterConfiguration() {
		return exportConfiguration;
	}

}
