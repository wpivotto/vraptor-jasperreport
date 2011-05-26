package br.com.caelum.vraptor.jasperreports;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletResponse;

import br.com.caelum.vraptor.interceptor.download.ByteArrayDownload;
import br.com.caelum.vraptor.interceptor.download.Download;

import com.google.common.io.Closeables;
import com.google.common.io.Files;
import com.google.common.io.Flushables;

public class ReportsDownload implements Download {

	private final String filename;
	private final ByteArrayOutputStream output;
	private final ZipOutputStream zip;
	private ReportExporter exporter;

	public ReportsDownload() {
		this("reports.zip");
	}

	public ReportsDownload(String filename) {
		this.filename = filename;
		this.output = new ByteArrayOutputStream();
		this.zip = new ZipOutputStream(output);
		this.exporter = new JasperExporter();
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

	public ReportsDownload add(Report report, ExportFormat format, boolean doCompress) {
		byte[] content = exporter.export(report).to(format);
		String name = report.getFileName() + "." + format.getExtension();
		add(name, content, doCompress);
		return this;
	}

	public ReportsDownload add(Report report, ExportFormat format) {
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
		close();
		new ByteArrayDownload(output.toByteArray(), "application/zip",
				filename, true).write(response);
	}

	private void close() {
		Flushables.flushQuietly(zip);
		Closeables.closeQuietly(zip);
	}

}