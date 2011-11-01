package br.com.caelum.vraptor.jasperreports.download;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import br.com.caelum.vraptor.interceptor.download.ByteArrayDownload;
import br.com.caelum.vraptor.interceptor.download.Download;
import br.com.caelum.vraptor.jasperreports.Report;
import br.com.caelum.vraptor.jasperreports.exporter.ReportExporter;
import br.com.caelum.vraptor.jasperreports.formats.ExportFormat;

import com.google.common.collect.Lists;

public class BatchReportsDownload implements Download {

	private ReportExporter exporter;
	private List<Report<?>> reports = Lists.newArrayList();
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
	
	public BatchReportsDownload add(List<Report<?>> reports){
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
		new ByteArrayDownload(getContent(), getContentType(), getFileName(), doDownload).write(response);
	}
	
	private byte[] getContent(){
		return exporter.export(reports).to(format);
	}
	
	public byte[] getContent(ReportExporter exporter){
		return exporter.export(reports).to(format);
	}

	public String getContentType(){
		return format.getContentType();
	}
	
	public String getFileName(){
		return filename + "." + format.getExtension();
	}

}