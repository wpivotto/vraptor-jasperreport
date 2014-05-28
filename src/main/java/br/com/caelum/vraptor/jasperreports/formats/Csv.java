package br.com.caelum.vraptor.jasperreports.formats;

import java.io.OutputStream;

import javax.enterprise.context.ApplicationScoped;

import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.export.Exporter;
import net.sf.jasperreports.export.ExporterConfiguration;
import net.sf.jasperreports.export.ExporterOutput;
import net.sf.jasperreports.export.ReportExportConfiguration;
import net.sf.jasperreports.export.SimpleCsvExporterConfiguration;
import net.sf.jasperreports.export.SimpleCsvReportConfiguration;
import net.sf.jasperreports.export.SimpleWriterExporterOutput;

/**
 * @author William Pivotto
 */

@ApplicationScoped
public class Csv extends AbstractExporter {

	public String getContentType() {
		return "application/csv";
	}

	public String getExtension() {
		return "csv";
	}

	@SuppressWarnings("rawtypes")
	public Exporter setup() {
		return new JRCsvExporter();
	}

	public ReportExportConfiguration getReportConfiguration() {
		return new SimpleCsvReportConfiguration();
	}
	
	public ExporterConfiguration getExporterConfiguration() {
		return new SimpleCsvExporterConfiguration();
	}
	
	public ExporterOutput getExporterOutput(OutputStream output) {
		return new SimpleWriterExporterOutput(output);
	}

}
