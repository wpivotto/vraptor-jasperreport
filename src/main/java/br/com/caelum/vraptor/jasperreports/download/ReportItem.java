package br.com.caelum.vraptor.jasperreports.download;

import java.util.Collection;

import br.com.caelum.vraptor.jasperreports.Report;
import br.com.caelum.vraptor.jasperreports.exporter.ReportExporter;
import br.com.caelum.vraptor.jasperreports.formats.ExportFormat;

import com.google.common.collect.Lists;

public class ReportItem {
	
	private final Collection<Report<?>> reports = Lists.newArrayList();
	private final ExportFormat format;
	private final String filename;
	
	public ReportItem(Report<?> report, ExportFormat format) {
		this.reports.add(report);
		this.format = format;
		this.filename = report.getFileName() + "." + format.getExtension();
	}
	
	public ReportItem(String filename, Collection<Report<?>> reports, ExportFormat format) {
		this.reports.addAll(reports);
		this.format = format;
		this.filename = filename + "." + format.getExtension();
	}

	public Collection<Report<?>> getReports() {
		return reports;
	}
	
	public byte[] getContent(ReportExporter exporter){
		return exporter.export(reports).to(format);
	}

	public ExportFormat getFormat() {
		return format;
	}
	
	public String getFilename(){
		return filename;
	}
	
	public String getContentType(){
		return format.getContentType();
	}

}
