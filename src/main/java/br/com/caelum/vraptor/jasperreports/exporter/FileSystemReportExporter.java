package br.com.caelum.vraptor.jasperreports.exporter;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.caelum.vraptor.jasperreports.Report;
import br.com.caelum.vraptor.jasperreports.ReportFormatResolver;
import br.com.caelum.vraptor.jasperreports.ReportPathResolver;
import br.com.caelum.vraptor.jasperreports.download.ReportItem;
import br.com.caelum.vraptor.jasperreports.download.ReportZipFile;
import br.com.caelum.vraptor.jasperreports.formats.ExportFormat;

import com.google.common.io.Files;

@RequestScoped
public class FileSystemReportExporter {
	
	@Inject private ReportExporter exporter;
	@Inject private ReportPathResolver pathResolver;
	@Inject private ReportFormatResolver formatResolver;
	private final Logger logger = LoggerFactory.getLogger(FileSystemReportExporter.class);

	public File export(ReportItem item) {
		return write(item.getContent(exporter), item.getFilename());
	}
	
	public File export(Report report) {
		return export(new ReportItem(report, formatResolver.getExportFormat()));
	}
	
	public File export(Report report, ExportFormat format) {
		return export(new ReportItem(report, format));
	}
	
	public File export(String filename, List<ReportItem> reports) {
		ReportZipFile zipFile = new ReportZipFile(filename);
		zipFile.addAll(reports);
		return write(zipFile.getContent(exporter), zipFile.getName() + ".zip");
	}
	
	public File export(String filename, ReportItem ...reports) {
		return export(filename, Arrays.asList(reports));
	}
	
	public File export(String filename, ExportFormat format, List<Report> reports) {
		return export(new ReportItem(filename, reports, format));
	}
	
	public File export(String filename, ExportFormat format, Report ...reports) {
		return export(filename, format, Arrays.asList(reports));
	}
	
	private File write(byte[] content, String fileName) {
		String path = pathResolver.getReportGenerationPath() + fileName;
		File file = new File(path);
		try {
			Files.write(content, file);
			logger.debug("File {} created", file.getAbsolutePath());
		} catch (IOException e) {
			logger.error("Error creating report file", e);
		}
		return file;
	}

}