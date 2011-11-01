package br.com.caelum.vraptor.jasperreports.download;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import br.com.caelum.vraptor.jasperreports.Report;
import br.com.caelum.vraptor.jasperreports.exporter.ReportExporter;
import br.com.caelum.vraptor.jasperreports.formats.ExportFormat;

import com.google.common.collect.Lists;
import com.google.common.io.Closeables;
import com.google.common.io.Flushables;


public class ReportZipFile {
	
	private ByteArrayOutputStream output = new ByteArrayOutputStream();
	private ZipOutputStream zip = new ZipOutputStream(output);
	private List<ReportEntry> entries = Lists.newArrayList();
	private String filename;
	
	public ReportZipFile(String filename){
		this.filename = filename;
	}
	
	public ReportZipFile add(Report<?> report, ExportFormat format){
		this.entries.add(new ReportEntry(report, format));
		return this;
	}
	
	public ReportZipFile add(String filename, Collection<Report<?>> reports, ExportFormat format) {
		this.entries.add(new ReportEntry(filename, reports, format));
		return this;
	}
	
	public ReportZipFile add(Collection<Report<?>> reports, ExportFormat format) {
		return add("reports", reports, format);
	}
	
	public byte[] getContent(ReportExporter exporter) {
	
		for(ReportEntry entry : entries){
			write(entry.getFilename(), entry.getContent(exporter));
		}
		
		Flushables.flushQuietly(zip);
		Closeables.closeQuietly(zip);
		return output.toByteArray();
		
	}
	
	public String getContentType(){
		return "application/zip";
	}
	
	public String getName(){
		return filename;
	}
	
	public void setName(String filename){
		this.filename = filename;
	}
	
	private void write(String name, byte[] content) {
		try {
			ZipEntry entry = new ZipEntry(name);
			entry.setSize(content.length);
			entry.setMethod(ZipEntry.DEFLATED);
			zip.putNextEntry(entry);
			zip.write(content);
			zip.closeEntry();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
