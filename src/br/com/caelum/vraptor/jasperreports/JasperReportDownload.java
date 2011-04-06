package br.com.caelum.vraptor.jasperreports;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import br.com.caelum.vraptor.interceptor.download.ByteArrayDownload;
import br.com.caelum.vraptor.interceptor.download.Download;

@SuppressWarnings("unchecked")
public class JasperReportDownload implements Download {
	
	private final Report report;
	private final ExportFormat format;
	private final boolean doDownload;
	
	
	public JasperReportDownload(Report report, ExportFormat format, boolean doDownload){
		this.report = report;
		this.format = format;
		this.doDownload = doDownload;
	}
	
	public JasperReportDownload(Report report, ExportFormat format){
		this(report, format, true);
	}

	public void write(HttpServletResponse response) throws IOException {
		byte[] bytes = new JasperExporter().export(report).to(format);
		String filename = report.getFileName() + format.getExtension();
		new ByteArrayDownload(bytes, format.getContentType(), filename, doDownload).write(response);
	}

}
