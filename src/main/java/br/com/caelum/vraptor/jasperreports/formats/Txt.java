package br.com.caelum.vraptor.jasperreports.formats;

import java.io.OutputStream;

import javax.enterprise.context.ApplicationScoped;

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
		SimpleTextReportConfiguration config = new SimpleTextReportConfiguration();
		config.setCharHeight(20f);
		config.setCharWidth(5f);
		return config;
	}

	public ExporterConfiguration getExporterConfiguration() {
		return new SimpleTextExporterConfiguration();
	}
	
	public ExporterOutput getExporterOutput(OutputStream output) {
		return new SimpleWriterExporterOutput(output);
	}

}
