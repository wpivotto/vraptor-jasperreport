package br.com.caelum.vraptor.jasperreports.download;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletResponse;

import br.com.caelum.vraptor.interceptor.download.ByteArrayDownload;
import br.com.caelum.vraptor.interceptor.download.Download;
import br.com.caelum.vraptor.jasperreports.Report;
import br.com.caelum.vraptor.jasperreports.exporter.ReportExporter;
import br.com.caelum.vraptor.jasperreports.formats.ExportFormat;

import com.google.common.io.Closeables;
import com.google.common.io.Files;
import com.google.common.io.Flushables;

public class ReportsDownload implements Download {

	private ReportExporter exporter;
	private List<ReportZipEntry> reports = new ArrayList<ReportZipEntry>();
	private final String filename;
	private final ByteArrayOutputStream output;
	private final ZipOutputStream zip;
	
	public ReportsDownload() {
		this("reports.zip");
	}

	public ReportsDownload(String filename) {
		this.filename = filename;
		this.output = new ByteArrayOutputStream();
		this.zip = new ZipOutputStream(output);
	}

	public ReportsDownload setLevel(int level) {
		this.zip.setLevel(level);
		return this;
	}
	
	public void setExporter(ReportExporter exporter) {
		this.exporter = exporter;
	}

	public ReportsDownload add(String name, byte[] content, boolean doCompress) {

		try {

			ZipEntry entry = new ZipEntry(name);
			entry.setSize(content.length);
			entry.setMethod(doCompress ? ZipEntry.DEFLATED : ZipEntry.STORED);
			zip.putNextEntry(entry);
			zip.write(content);
			zip.closeEntry();

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
		return this;
		
	}
	
	public ReportsDownload add(String name, byte[] content) {
		return add(name, content, true);
	}

	public ReportsDownload add(Report<?> report, ExportFormat format, boolean doCompress) {
		ReportZipEntry entry = new ReportZipEntry(report, format, doCompress);
		this.reports.add(entry);
		return this;
	}

	public ReportsDownload add(Report<?> report, ExportFormat format) {
		return add(report, format, true);
	}

	public ReportsDownload add(File file, boolean doCompress) throws IOException {
		byte[] content = Files.toByteArray(file);
		add(file.getName(), content, doCompress);
		return this;
	}

	public ReportsDownload add(File file) throws IOException {
		return add(file, true);
	}
	
	public void write(HttpServletResponse response) throws IOException {
		writeReports();
		new ByteArrayDownload(output.toByteArray(), "application/zip",
				filename, true).write(response);
	}
	
	private void writeReports(){
		
		for(ReportZipEntry entry : reports){
			Report<?> report = entry.getReport();
			ExportFormat format = entry.getFormat();
			byte[] content = exporter.export(report).to(entry.getFormat());
			String name = report.getFileName() + "." + format.getExtension();
			add(name, content, entry.getCompressionMethod());
		}
		
		Flushables.flushQuietly(zip);
		Closeables.closeQuietly(zip);
		
	}

}