package br.com.caelum.vraptor.jasperreports.download;

import br.com.caelum.vraptor.jasperreports.Report;
import br.com.caelum.vraptor.jasperreports.formats.ExportFormat;

public class ReportZipEntry {
	
	private final Report<?> report;
	private final ExportFormat format;
	private final boolean method;
	
	public ReportZipEntry(Report<?> report, ExportFormat format, boolean doCompress) {
		this.report = report;
		this.format = format;
		this.method = doCompress;
	}

	public Report<?> getReport() {
		return report;
	}

	public ExportFormat getFormat() {
		return format;
	}

	public boolean getCompressionMethod() {
		return method;
	}

}
