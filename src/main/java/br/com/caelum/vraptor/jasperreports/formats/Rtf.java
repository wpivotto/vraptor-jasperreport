package br.com.caelum.vraptor.jasperreports.formats;

import java.io.OutputStream;

import javax.enterprise.context.ApplicationScoped;

import net.sf.jasperreports.engine.export.JRRtfExporter;
import net.sf.jasperreports.export.Exporter;
import net.sf.jasperreports.export.ExporterConfiguration;
import net.sf.jasperreports.export.ExporterOutput;
import net.sf.jasperreports.export.ReportExportConfiguration;
import net.sf.jasperreports.export.SimpleRtfExporterConfiguration;
import net.sf.jasperreports.export.SimpleRtfReportConfiguration;
import net.sf.jasperreports.export.SimpleWriterExporterOutput;

/**
 * @author William Pivotto
 */

@ApplicationScoped
public class Rtf extends AbstractExporter {

	@SuppressWarnings("rawtypes")
	public Exporter setup() {
		return new JRRtfExporter();
	}

	public String getContentType() {
		return "application/rtf";
	}

	public String getExtension() {
		return "rtf";
	}

	public ReportExportConfiguration getReportConfiguration() {
		return new SimpleRtfReportConfiguration();
	}

	public ExporterConfiguration getExporterConfiguration() {
		return new SimpleRtfExporterConfiguration();
	}
	
	public ExporterOutput getExporterOutput(OutputStream output) {
		return new SimpleWriterExporterOutput(output);
	}

}
