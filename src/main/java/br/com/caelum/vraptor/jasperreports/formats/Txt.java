package br.com.caelum.vraptor.jasperreports.formats;

import java.io.OutputStream;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRTextExporter;
import net.sf.jasperreports.export.Exporter;
import net.sf.jasperreports.export.ExporterConfiguration;
import net.sf.jasperreports.export.ExporterOutput;
import net.sf.jasperreports.export.ReportExportConfiguration;
import net.sf.jasperreports.export.SimpleTextExporterConfiguration;
import net.sf.jasperreports.export.SimpleTextReportConfiguration;
import net.sf.jasperreports.export.SimpleWriterExporterOutput;

/**
 * @author William Pivotto
 */

@ApplicationScoped
public class Txt extends AbstractExporter {
	
	protected SimpleTextReportConfiguration reportConfiguration = new SimpleTextReportConfiguration();
	protected SimpleTextExporterConfiguration exportConfiguration = new SimpleTextExporterConfiguration();

	public String getContentType() {
		return "text/plain";
	}

	public String getExtension() {
		return "txt";
	}
	
	@SuppressWarnings("rawtypes")
	public Exporter setup() {
		return new JRTextExporter();
	}
	
	public boolean supportsBatchMode() {
		return false;
	}

	public ReportExportConfiguration getReportConfiguration() {
		reportConfiguration.setCharHeight(13.948f);
		reportConfiguration.setCharWidth(7.238f);
		return reportConfiguration;
	}

	public ExporterConfiguration getExporterConfiguration() {
		return exportConfiguration;
	}
	
	public ExporterOutput getExporterOutput(OutputStream output) {
		return new SimpleWriterExporterOutput(output);
	}

}
