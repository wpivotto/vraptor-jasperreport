package br.com.caelum.vraptor.jasperreports.download;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import br.com.caelum.vraptor.jasperreports.Report;
import br.com.caelum.vraptor.jasperreports.exporter.ReportExporter;
import br.com.caelum.vraptor.jasperreports.formats.ExportFormat;
import br.com.caelum.vraptor.observer.download.ByteArrayDownload;
import br.com.caelum.vraptor.observer.download.Download;

public class ReportsDownload implements Download {

	private ReportExporter exporter;
	private ReportZipFile file;
	
	public ReportsDownload() {
		this("reports.zip");
	}

	public ReportsDownload(String filename) {
		this.file = new ReportZipFile(filename);
	}

	public void setExporter(ReportExporter exporter) {
		this.exporter = exporter;
	}

	public ReportsDownload add(Report report, ExportFormat format) {
		this.file.add(report, format);
		return this;
	}
	
	public ReportsDownload add(String filename, List<Report> reports, ExportFormat format) {
		this.file.add(filename, reports, format);
		return this;
	}
	
	public void write(HttpServletResponse response) throws IOException {
		new ByteArrayDownload(getContent(), file.getContentType(), file.getName(), true).write(response);
	}
	
	private byte[] getContent(){
		return file.getContent(exporter);
	}
	
	public byte[] getContent(ReportExporter exporter){
		return file.getContent(exporter);
	}

	public String getContentType(){
		return file.getContentType();
	}
	
	public String getFileName(){
		return file.getName();
	}
}