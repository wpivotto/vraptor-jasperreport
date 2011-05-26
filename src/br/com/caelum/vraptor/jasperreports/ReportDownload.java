package br.com.caelum.vraptor.jasperreports;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import br.com.caelum.vraptor.interceptor.download.ByteArrayDownload;
import br.com.caelum.vraptor.interceptor.download.Download;

/**
 * Reads bytes from a report into the result.
 *
 * @author William Pivotto
 *
 */

public class ReportDownload implements Download {
	
	private final Report<?> report;
	private final ExportFormat format;
	private final boolean doDownload;
	private ReportExporter exporter;
	
	public ReportDownload(Report<?> report, ExportFormat format, boolean doDownload){
		this.report = report;
		this.format = format;
		this.doDownload = doDownload;
		this.exporter = new JasperExporter();
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
