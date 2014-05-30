package br.com.caelum.vraptor.jasperreports.formats;

import java.io.OutputStream;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import net.sf.jasperreports.engine.JasperPrint;
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
	
	protected SimpleCsvReportConfiguration reportConfiguration = new SimpleCsvReportConfiguration();
	protected SimpleCsvExporterConfiguration exportConfiguration = new SimpleCsvExporterConfiguration();

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
		return reportConfiguration;
	}
	
	public ExporterConfiguration getExporterConfiguration() {
		return exportConfiguration;
	}
	
	public ExporterOutput getExporterOutput(OutputStream output) {
		return new SimpleWriterExporterOutput(output);
	}

}
