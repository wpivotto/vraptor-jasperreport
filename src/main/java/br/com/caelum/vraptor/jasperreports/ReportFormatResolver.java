package br.com.caelum.vraptor.jasperreports;


import javax.enterprise.context.RequestScoped;

import br.com.caelum.vraptor.http.FormatResolver;
import br.com.caelum.vraptor.jasperreports.formats.ExportFormat;
import br.com.caelum.vraptor.jasperreports.formats.ExportFormats;

@RequestScoped
public class ReportFormatResolver {

	private final ExportFormats formats;
	private final FormatResolver formatResolver;
	
	public ReportFormatResolver(ExportFormats formats, FormatResolver formatResolver) {
		this.formats = formats;
		this.formatResolver = formatResolver;
	}
	
	public ExportFormat getExportFormat(){
		return formats.byExtension(formatResolver.getAcceptFormat());
	}
	
	public boolean doDownload(){
		return !"html".equals(formatResolver.getAcceptFormat());
	}
}
