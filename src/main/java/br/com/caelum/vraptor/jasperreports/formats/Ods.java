package br.com.caelum.vraptor.jasperreports.formats;

import javax.enterprise.context.ApplicationScoped;

import net.sf.jasperreports.engine.export.oasis.JROdsExporter;
import net.sf.jasperreports.export.Exporter;
import net.sf.jasperreports.export.ExporterConfiguration;
import net.sf.jasperreports.export.ReportExportConfiguration;
import net.sf.jasperreports.export.SimpleOdsExporterConfiguration;
import net.sf.jasperreports.export.SimpleOdsReportConfiguration;

/**
 * @author William Pivotto
 */

@ApplicationScoped
public class Ods extends AbstractExporter {
	
	public String getContentType() {
		return "application/vnd.oasis.opendocument.spreadsheet";
	}

	public String getExtension() {
		return "ods";
	}
	
	@SuppressWarnings("rawtypes")
	public Exporter setup() {
		return new JROdsExporter();
	}

	public ReportExportConfiguration getReportConfiguration() {
		return new SimpleOdsReportConfiguration();
	}

	public ExporterConfiguration getExporterConfiguration() {
		return new SimpleOdsExporterConfiguration();
	}

}
