package br.com.caelum.vraptor.jasperreports.download;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import br.com.caelum.vraptor.interceptor.download.ByteArrayDownload;
import br.com.caelum.vraptor.interceptor.download.Download;
import br.com.caelum.vraptor.jasperreports.Report;
import br.com.caelum.vraptor.jasperreports.exporter.ReportExporter;
import br.com.caelum.vraptor.jasperreports.formats.ExportFormat;

/**
 * Reads bytes from a report into the result.
 *
 * @author William Pivotto
 *
 */

public class ReportDownload implements Download {
	
	private ReportExporter exporter;
	private final Report<?> report;
	private final ExportFormat format;
	private final boolean doDownload;
	
	public ReportDownload(Report<?> report, ExportFormat format, boolean doDownload){
		this.report = report;
		this.format = format;
		this.doDownload = doDownload;
	}
	
	public ReportDownload(Report<?> report, ExportFormat format){
		this(report, format, true);
	}

	public void setExporter(ReportExporter exporter) {
		this.exporter = exporter;
	}

	public void write(HttpServletResponse response) throws IOException {
		byte[] bytes = exporter.export(report).to(format);
		String filename = report.getFileName() + "." + format.getExtension();
		new ByteArrayDownload(bytes, format.getContentType(), filename, doDownload).write(response);
	}

}
