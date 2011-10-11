package br.com.caelum.vraptor.jasperreports.download;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import br.com.caelum.vraptor.interceptor.download.ByteArrayDownload;
import br.com.caelum.vraptor.interceptor.download.Download;
import br.com.caelum.vraptor.jasperreports.Report;
import br.com.caelum.vraptor.jasperreports.exporter.ReportExporter;
import br.com.caelum.vraptor.jasperreports.formats.ExportFormat;

public class BatchReportsDownload implements Download {

	private ReportExporter exporter;
	private List<Report<?>> reports = new ArrayList<Report<?>>();
	private final ExportFormat format;
	private final boolean doDownload;
	private String filename;
	
	public BatchReportsDownload(ExportFormat format, String filename, boolean doDownload){
		if(!format.supportsBatchMode())
			throw new IllegalArgumentException("This export format is not supported in batch mode");
		this.format = format;
		this.filename = filename;
		this.doDownload = doDownload;
	}
	
	public BatchReportsDownload(ExportFormat format){
		this(format, "reports", true);
	}

	public void setExporter(ReportExporter exporter) {
		this.exporter = exporter;
	}
	
	public BatchReportsDownload add(Collection<Report<?>> reports){
		reports.addAll(reports);
		return this;
	}
	
	public BatchReportsDownload add(Report<?> ...reports){
		for(Report<?> report : reports){
			this.reports.add(report);
		}
		return this;
	}

	public void write(HttpServletResponse response) throws IOException {
		byte[] bytes = exporter.export(reports).to(format);
		new ByteArrayDownload(bytes, format.getContentType(), filename + "." + format.getExtension(), doDownload).write(response);
	}

}